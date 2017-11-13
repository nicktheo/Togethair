package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.service.FlightService;
import com.realdolmen.togethair.web.BookingBean;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@FlowScoped("searching")
public class SearchController implements Serializable {

    @Inject
    BookingBean booking;

    @Inject
    FlightService flightService;

    private List<Airport> airports;

    private TicketType ticketType = TicketType.ONE_WAY;

    private Airport origin;
    private Airport destination;

    private LocalDate departureDate;
    private LocalDate returnDate;

    private TravelClassType travelClass;
    private int passengerCount;

    private TicketType resultStage = TicketType.ONE_WAY;

    private List<TravelClass> outboundFlights;
    private List<TravelClass> returnFlights;


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

    public List<TravelClass> getResults() {
        if (resultStage == TicketType.ONE_WAY)
            return outboundFlights;
        else
            return returnFlights;
    }

    public String submit() {
        outboundFlights = flightService.findAvailableFlights(origin, destination, departureDate, travelClass, passengerCount);
        returnFlights = flightService.findAvailableFlights(destination, origin, returnDate, travelClass, passengerCount);
        return "list";
    }

    public String book(TravelClass flight) {
        booking.addFlight(flight);

        if (resultStage == TicketType.RETURN || ticketType == TicketType.ONE_WAY)
            return "book";

        resultStage = TicketType.RETURN;
        return "list";
    }
}
