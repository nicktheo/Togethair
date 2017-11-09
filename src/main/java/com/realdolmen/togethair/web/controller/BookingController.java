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
import com.realdolmen.togethair.service.TravelClassService;
import com.realdolmen.togethair.web.BookingBean;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@FlowScoped("booking")
public class BookingController {

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
    private int amount;
    private List<Passenger> passengers;

    @PostConstruct
    public void initialize() {
        travelClassIds = new ArrayList<>();
        passengers = new ArrayList<>();
    }



    public String checkout() {
        if (!userBean.isLoggedIn()){
            return "login.xhtml";
        }
        for (int i = 0; i < amount; i++) {
            passengers.add(new SimplePassenger());
        }
        return "booking.xhtml";
    }

    public String addBooking() {
        if (!userBean.isLoggedIn()){
            return "login.xhtml";
        }
        try{
            List<TravelClass> tClasses = bookingBean.getTravelClasses();
            bookingBuilder.setCustomer(userBean.getCustomer()).addFlights(tClasses).addPassengers(passengers);
            for (TravelClass tcItem : tClasses) {
                bookingBuilder.addPriceAdapter(pricingProvider.getFlightPricingAdapters(tcItem.getFlight()), tcItem);
            }
            persistBooking.persistBooking(bookingBuilder, amount);

        } catch (ObjectNotFoundException e) {
            return "somethingWentWrong.xhtml";
        } catch (DuplicateFlightException e) {
            return "somethingWentWrong.xhtml";
        } catch (DuplicatePassengerException e) {
            return "somethingWentWrong.xhtml";
        } catch (SeatIsTakenException e) {
            return "seatTaken.xhtml";
        } catch (DuplicateSeatException e) {
            return "somethingWentWrong.xhtml";
        }

        return "bookingSuccesfull.xhtml";
    }
}
