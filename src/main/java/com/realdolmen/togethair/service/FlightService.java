package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import com.realdolmen.togethair.repository.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<Flight> findAvailableFlights(Airport origin, Airport destination, LocalDate departureDate, TravelClassType travelClass, int seatCount) {
        return flightRepository.findAvailableFlights(origin, destination, departureDate, travelClass, seatCount);
    }

    public List<Airport> getAllAirports() {
        return flightRepository.getAllAirports();
    }
}
