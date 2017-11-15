package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.service.FlightService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Named
@RequestScoped
public class SearchRequest {

    @Inject
    FlightService flightService;


    private TicketType ticketType = TicketType.ONE_WAY;

    private Airport origin;
    private Airport destination;

    private LocalDate departureDate;
    private LocalDate returnDate;

    private TravelClassType travelClass;
    private int passengerCount;


    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

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
        return flightService.getAllAirports();
    }

    public TravelClassType[] getTravelClasses() {
        return TravelClassType.values();
    }

    public int[] getPassengerCountOptions() {
        return IntStream.rangeClosed(1, 10).toArray();
    }
}
