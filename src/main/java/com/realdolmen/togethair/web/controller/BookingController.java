package com.realdolmen.togethair.web.controller;


import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.PricingAdapter;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.service.*;
import com.realdolmen.togethair.web.BookingDetails;
import com.realdolmen.togethair.web.UserDetails;

import javax.ejb.ObjectNotFoundException;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@FlowScoped("booking")
public class BookingController implements Serializable{

    @Inject
    BookingDetails bookingDetails;
    @Inject
    UserDetails userDetails;
    @Inject
    PricingProvider pricingProvider;
    @Inject
    BookingService bookingService;
    @Inject
    QRCodeProvider qrCodeProvider;
    @Inject
    EmailService emailService;

    private Booking.Builder bookingBuilder = new Booking.Builder();
    private List<Passenger> passengers = new ArrayList<>();

    private String paymentMethod;
    private String ccNumber;

    private String bookingId;


    public void checkout() {
        for (int i = 0; i < bookingDetails.getPassengerCount(); i++)
            passengers.add(new SimplePassenger("", "", ""));
    }

    public String addBooking() {
        Booking.Builder bookingBuilder = new Booking.Builder();
        try{
            List<TravelClass> tClasses = bookingDetails.getFlights();
            bookingBuilder.setCustomer(userDetails.getUser()).addFlights(tClasses).addPassengers(passengers);

            PricingAdapter pa;
            for (TravelClass tcItem : tClasses) {
                pa = pricingProvider.getFlightPricingAdapters(tcItem.getFlight());
                bookingBuilder.addPriceAdapter(pa, tcItem);
            }
            if (paymentMethod.equals("creditcard")) {
                bookingBuilder.addPriceAdapter(pricingProvider.getBookingPricingAdapter("CREDIT_CARD"));
            }
            Booking temp = bookingService.persistBooking(bookingBuilder, bookingDetails.getPassengerCount());
            this.bookingId = temp.getUuid();
            temp.setSeatAvailability(Availability.TAKEN);
            //bookingDetails.clearFlights();
            //emailService.sendEmail(temp);

        } catch (DuplicateFlightException e) {
            return "somethingWentWrong";
        } catch (DuplicatePassengerException e) {
            return "somethingWentWrong";
        } catch (SeatAlreadyTakenException e) {
            return "seatTaken";
        } catch (DuplicateSeatException e) {
            return "omethingWentWrong.xhtml";
        } catch (ObjectNotFoundException e) {
            return "somethingWentWrong";
        }

        return "success";
    }

    public String provideQrCode() {
        return qrCodeProvider.generateBase64QrCode(bookingId);
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
