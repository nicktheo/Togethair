package com.realdolmen.togethair.web.controller;


import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.PricingAdapter;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatIsTakenException;
import com.realdolmen.togethair.service.*;
import com.realdolmen.togethair.web.BookingBean;
import com.realdolmen.togethair.web.LoginBean;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@FlowScoped("booking")
public class BookingController implements Serializable{

    @Inject
    SeatService seatService;
    @Inject
    BookingBean bookingBean;
    @Inject
    LoginBean loginBean;
    @Inject
    Booking.Builder bookingBuilder;
    @Inject
    PricingProvider pricingProvider;
    @Inject
    BookingService bookingService;
    @Inject
    QRCodeProvider qrCodeProvider;
    @Inject
    EmailService emailService;

    private List<Long> travelClassIds;
    private List<Passenger> passengers;

    private String paymentMethod;
    private String ccNumber;

    private long bookingId;


    @PostConstruct
    public void initialize() {
        travelClassIds = new ArrayList<>();
        passengers = new ArrayList<>();
    }



    public String checkout() {
        for (int i = 0; i < bookingBean.getPassengerCount(); i++) {
            passengers.add(new SimplePassenger("", "", ""));
        }
        return "book";
    }

    public String addBooking() {
        try{
            List<TravelClass> tClasses = bookingBean.getFlights();
            bookingBuilder.setCustomer(loginBean.getCustomer()).addFlights(tClasses).addPassengers(passengers);
            PricingAdapter pa;
            for (TravelClass tcItem : tClasses) {
                pa = pricingProvider.getFlightPricingAdapters(tcItem.getFlight());
                bookingBuilder.addPriceAdapter(pa, tcItem);
            }
            if (paymentMethod.equals("creditcard")) {
                bookingBuilder.addPriceAdapter(pricingProvider.getBookingPricingAdapter("CREDIT_CARD"));
            }
            Booking temp = bookingService.persistBooking(bookingBuilder, bookingBean.getPassengerCount());
            this.bookingId = temp.getId();
            emailService.sendEmail(temp);

        } catch (DuplicateFlightException e) {
            return "somethingWentWrong";
        } catch (DuplicatePassengerException e) {
            return "somethingWentWrong";
        } catch (SeatIsTakenException e) {
            return "seatTaken";
        } catch (DuplicateSeatException e) {
            return "somethingWentWrong";
        } catch (ObjectNotFoundException e) {
            return "somethingWentWrong";
        }

        return "end";
    }

    public OutputStream provideQrCode() {
        return qrCodeProvider.generateQrCode(bookingId);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }
}
