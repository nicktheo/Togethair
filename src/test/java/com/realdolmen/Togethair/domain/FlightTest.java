package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import com.realdolmen.togethair.domain.flight.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightTest {

    Flight Flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
        Airport destinationAirport = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
        TravelClass planeClassFirst;
        Seat seat = new Seat(5, 10, Availability.FREE, new TravelClass());
        List<Seat> seats = new ArrayList<>();
        List<TravelClass> availability = new ArrayList<>();

        seats.add(seat);

        planeClassFirst = new TravelClass(TravelClassType.FIRST,200,seats,Flight);
        availability.add(planeClassFirst);
        Flight flight = new Flight(departureAirport,destinationAirport,date,"3 hours",availability);
    }

    @Test
    public void testSpecificFlight() {
        Assert.assertEquals(GlobalRegion.NORTHERN_EUROPE,Flight.getDepartureAirport().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.SOUTHERN_EUROPE,Flight.getDestinationAirport().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",Flight.getDepartureAirport().getName());
        Assert.assertEquals("Athens International Airport",Flight.getDestinationAirport().getName());

        Assert.assertEquals(2017,Flight.getDateTime().getYear()+1900); // wtf

        Assert.assertEquals(TravelClassType.FIRST,Flight.getAvailability().get(0).getTravelClassType());

    }
}
