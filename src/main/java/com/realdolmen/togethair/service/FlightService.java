package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.repository.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@RequestScoped
public class FlightService {

    @Inject
    private FlightRepository flightRepository;

    public List<TravelClass> findAvailableFlights(Airport origin, Airport destination, LocalDate departureDate, TravelClassType travelClass, int seatCount) {
        return flightRepository.findAvailableFlights(origin, destination, departureDate, travelClass, seatCount);
    }

    public List<Airport> getAllAirports() {
        return flightRepository.getAllAirports();
    }
}
