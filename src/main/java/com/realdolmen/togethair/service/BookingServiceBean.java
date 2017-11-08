package com.realdolmen.togethair.service;


import com.realdolmen.togethair.domain.booking.BookingBuilder;
import com.realdolmen.togethair.repository.SeatRepository;
import com.realdolmen.togethair.repository.TravelClassRepository;

import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@FlowScoped("booking")
public class BookingServiceBean {

    @Inject
    BookingBuilder builder;
    @Inject
    SeatRepository seatRepository;
    @Inject
    TravelClassRepository travelClassRepository;
    @Inject
    UserBean userBean;

    private List<String> firstnames;
    private List<String> lastnames;

    @PostConstruct
    public void initialize() {
        firstnames = new ArrayList<>();
        lastnames = new ArrayList<>();
        builder = new BookingBuilder();
    }

    public String addBookingLine(List<Long> TravelClassIds, int amount) {
        for (Long tcId : TravelClassIds) {
                builder.addFlight(travelClassRepository.getTravelClassById(tcId));
                builder.addPasengers
            }
            return null;
        }

//        builder.createBookingLine();
//        try {
//            TravelClass tc = travelClassRepository.getTravelClassById(travelClassId);
//        } catch (ObjectNotFoundException e) {
//            return "FlightNotFound.xhtml";
//        }

//    }
}
