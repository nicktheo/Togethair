package com.realdolmen.togethair.web.controller;


import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatIsTakenException;
import com.realdolmen.togethair.repository.AddSeatsAndPersistBookingTransaction;
import com.realdolmen.togethair.service.PricingProvider;
import com.realdolmen.togethair.service.SeatService;
import com.realdolmen.togethair.web.BookingBean;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@Named
@FlowScoped("booking")
public class BookingController implements Serializable{

    @Inject
    SeatService seatService;
    @Inject
    BookingBean bookingBean;
    @Inject
    LoginController userBean;
    @Inject
    Booking.Builder bookingBuilder;
    @Inject
    PricingProvider pricingProvider;
    @Inject
    AddSeatsAndPersistBookingTransaction persistBooking;

    private List<Long> travelClassIds;
    private List<Passenger> passengers;

    private String paymentMethod;
    private String ccNumber;

    @PostConstruct
    public void initialize() {
        travelClassIds = new ArrayList<>();
        passengers = new ArrayList<>();
    }



    public String checkout() {
        if (!userBean.isLoggedIn()){
            return "login";
        }
        for (int i = 0; i < bookingBean.getNumberOfPassengers(); i++) {
            passengers.add(new SimplePassenger());
        }
        return "book";
    }

    public String addBooking() {
        if (!userBean.isLoggedIn()){
            return "login";
        }
        try{
            List<TravelClass> tClasses = bookingBean.getTravelClasses();
            bookingBuilder.setCustomer(userBean.getCustomer()).addFlights(tClasses).addPassengers(passengers);
            for (TravelClass tcItem : tClasses) {
                bookingBuilder.addPriceAdapter(pricingProvider.getFlightPricingAdapters(tcItem.getFlight()), tcItem);
            }
            if (paymentMethod.equals("margin")) {
                bookingBuilder.addPriceAdapter(pricingProvider.getBookingPricingAdapter("creditcard"));
            }
            persistBooking.persistBooking(bookingBuilder, bookingBean.getNumberOfPassengers());

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

    public List<Passenger> getPassengers(){
        return passengers;
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
}
