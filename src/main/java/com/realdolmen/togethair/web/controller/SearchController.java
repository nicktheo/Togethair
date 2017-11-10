package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.time.LocalDate;

@Named
@SessionScoped
public class SearchController {

    private TicketType ticketType;

    private Airport origin;
    private Airport destination;

    private LocalDate departureDate;
    private LocalDate returnDate;

    private TravelClassType travelClass;
    private int passengerCount;
}
