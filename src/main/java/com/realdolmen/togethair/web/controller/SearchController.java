package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.service.FlightService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class SearchController implements Serializable {

    @Inject
    FlightService flightService;

    private static Map<Airport, String> airports;

    private TicketType ticketType;

    private Airport origin;
    private Airport destination;

    private LocalDate departureDate;
    private LocalDate returnDate;

    private TravelClassType travelClass;
    private int passengerCount;


    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport airport) {
        origin = airport;
    }


    public Map<Airport, String> getAirports() {
        if (airports == null)
            airports = flightService.getAllAirports().stream()
                    .collect(Collectors.toMap(Function.identity(), Airport::getCode));

        return airports;
    }
}
