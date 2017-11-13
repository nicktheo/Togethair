package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.Country;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import com.realdolmen.togethair.domain.flight.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightTest {

    Flight flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("EBBR", "Brussels Airport", Country.BEL, GlobalRegion.EUROPE);
        Airport destinationAirport = new Airport("LGAV", "Athens International Airport", Country.GRC, GlobalRegion.EUROPE);

        List<TravelClass> availability = new ArrayList<>();
        List<Seat> seats = new ArrayList<>();

        LocalDateTime ts = LocalDateTime.of(2017,11,9,14,0,0);
        Flight flight = new Flight(departureAirport,destinationAirport,ts, Duration.ofHours(3),availability);

        TravelClass planeClassFirst = new TravelClass(flight, TravelClassType.FIRST_CLASS,200, seats);
        availability.add(planeClassFirst);

        Seat seat = new Seat(5, 10, planeClassFirst, Availability.FREE);

        seats.add(seat);
    }

    @Test
    public void testSpecificFlight() {
        Assert.assertEquals(GlobalRegion.EUROPE, flight.getOrigin().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.EUROPE, flight.getDestination().getGlobalRegion());
        Assert.assertEquals("Brussels Airport", flight.getOrigin().getName());
        Assert.assertEquals("Athens International Airport", flight.getDestination().getName());

        Assert.assertEquals(2017, flight.getDeparture().getYear()+1900); // wtf

        Assert.assertEquals(TravelClassType.FIRST_CLASS, flight.getAvailability().get(0).getType());

    }
}
