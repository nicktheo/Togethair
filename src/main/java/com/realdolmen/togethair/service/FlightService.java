package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import com.realdolmen.togethair.repository.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
@RequestScoped
public class FlightService {

    @Inject
    private FlightRepository flightRepository;

    public List<Flight> findFlightsByAirportNameDateTimesAndAmountOfFreeSeats(Airport origin, Airport destination, int amount, LocalDateTime after, LocalDateTime before) {
        return flightRepository.findFlightsByAirportNameDateTimesAndAmountOfFreeSeats(origin, destination, amount, after, before);
    }

    public List<Flight> findFlightsByGlobalRegionDateTimesAndAmountOfFreeSeats(GlobalRegion origin, GlobalRegion destination, int amount, LocalDateTime after, LocalDateTime before) {
        return flightRepository.findFlightsByGlobalRegionDateTimesAndAmountOfFreeSeats(origin, destination, amount, after, before);
    }
}
