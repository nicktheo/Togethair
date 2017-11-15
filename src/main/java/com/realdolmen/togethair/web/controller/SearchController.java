package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.TicketType;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.service.FlightService;
import com.realdolmen.togethair.service.PricingProvider;
import com.realdolmen.togethair.web.BookingDetails;
import com.realdolmen.togethair.web.SearchRequest;

import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Named
@FlowScoped("search")
public class SearchController implements Serializable {

    @Inject
    SearchRequest searchRequest;

    @Inject
    FlightService flightService;

    @Inject
    PricingProvider pricingProvider;

    @Inject
    BookingDetails booking;

    private TicketType resultStage = TicketType.ONE_WAY;
    private TicketType ticketType;
    private List<TravelClass> outboundFlights;
    private List<TravelClass> returnFlights;
    private TravelClass selectedFlight;


    @PostConstruct
    public void initialize() {
        ticketType = searchRequest.getTicketType();

        Airport origin = searchRequest.getOrigin();
        Airport destination = searchRequest.getDestination();

        LocalDate departureDate = searchRequest.getDepartureDate();
        LocalDate returnDate = searchRequest.getReturnDate();

        TravelClassType travelClass = searchRequest.getTravelClass();
        int passengerCount = searchRequest.getPassengerCount();

        outboundFlights = flightService.findAvailableFlights(origin, destination, departureDate, travelClass, passengerCount);

        if (ticketType == TicketType.RETURN)
            returnFlights = flightService.findAvailableFlights(destination, origin, returnDate, travelClass, passengerCount);

        booking.setPassengerCount(searchRequest.getPassengerCount());
    }


    public TravelClass getSelectedFlight() {
        return selectedFlight;
    }


    public List<TravelClass> getResults() {
        if (resultStage == TicketType.ONE_WAY)
            return outboundFlights;
        else
            return returnFlights;
    }

    public String showDetails(TravelClass travelClass) {
        selectedFlight = travelClass;
        return "details";
    }

    public String addToBooking(TravelClass flight) {
        booking.addFlight(flight);

        if (resultStage == TicketType.RETURN || ticketType == TicketType.ONE_WAY)
            return "checkout";

        resultStage = TicketType.RETURN;
        return "results";
    }

    public String addToBooking() {
        return addToBooking(selectedFlight);
    }
}
