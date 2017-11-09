package com.realdolmen.togethair.service;


import com.realdolmen.togethair.domain.booking.BookingBuilder;
import com.realdolmen.togethair.repository.SeatRepository;
import com.realdolmen.togethair.repository.TravelClassRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@SessionScoped
public class BookingServiceBean {

    @Inject
    BookingBuilder builder;
    @Inject
    SeatRepository seatRepository;
    @Inject
    TravelClassRepository travelClassRepository;
    @Inject
    UserBean userBean;

    private List<Long> travelClassIds;
    private int amount;
    private List<Passenger> passengers;

    @PostConstruct
    public void initialize() {
        travelClassIds = new ArrayList<>();
        passengers = new ArrayList<>();
        builder = new BookingBuilder();
    }

    public String addFlights(List<Long> travelClassIds, int amount) {
        this.amount = amount;
        this.travelClassIds.addAll(travelClassIds);
        return "searchFlights";
    }

    public String checkout() {
        for (int i = 0; i < amount; i++) {
            passengers.add(new Passenger());
        }
        return "booking.xhtml";
    }

    public String addBookingLine() {
            throw new UnsupportedOperationException();
        }

//        builder.createBookingLine();
//        try {
//            TravelClass tc = travelClassRepository.getTravelClassById(travelClassId);
//        } catch (ObjectNotFoundException e) {
//            return "FlightNotFound.xhtml";
//        }

//    }
}
