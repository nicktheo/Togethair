package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.service.FlightService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class SearchController implements Serializable {

    @Inject
    FlightService flightService;

    private List<Airport> airports;

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

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public TravelClassType getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClassType travelClass) {
        this.travelClass = travelClass;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }


    public TicketType[] getTicketTypes() {
        return TicketType.values();
    }

    public List<Airport> getAirports() {
        if (airports == null)
            airports = flightService.getAllAirports();

        return airports;
    }

    public TravelClassType[] getTravelClasses() {
        return TravelClassType.values();
    }

    public List<Flight> getResults() {
        return flightService.findAvailableFlights(origin, destination, departureDate, travelClass, passengerCount);
    }

    public String submit() {
        return "results";
    }
}
