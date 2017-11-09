package com.realdolmen.togethair.service;


import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.BookingBuilder;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatIsTakenException;
import com.realdolmen.togethair.repository.AddSeatsAndPersistBookingTransaction;
import com.realdolmen.togethair.repository.SeatRepository;
import com.realdolmen.togethair.repository.TravelClassRepository;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@SessionScoped
public class BookingServiceBean {

    @Inject
    SeatRepository seatRepository;
    @Inject
    TravelClassRepository travelClassRepository;
    @Inject
    UserBean userBean;
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

    public String addFlights(List<Long> travelClassIds, int amount) {
        this.amount = amount;
        this.travelClassIds.addAll(travelClassIds);
        return "searchFlights";
    }

    public String checkout() {
        for (int i = 0; i < amount; i++) {
            passengers.add(new SimplePassenger());
        }
        return "booking.xhtml";
    }

    public String addBooking() {
        List<TravelClass> tClasses = new ArrayList<>();
        try{
            for (Long id : travelClassIds) {
                tClasses.add(travelClassRepository.getTravelClassById(id));
            }
            bookingBuilder.setCustomer().addFlights(tClasses).addPassengers(passengers);
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
    }
}
