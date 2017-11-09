package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.location.Airport;
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

    Flight Flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
        Airport destinationAirport = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
        TravelClass planeClassFirst;
        Seat seat = new Seat(5, 10, new TravelClass(), Availability.FREE);
        List<Seat> seats = new ArrayList<>();
        List<TravelClass> availability = new ArrayList<>();

        seats.add(seat);

        planeClassFirst = new TravelClass(Flight, TravelClassType.FIRST_CLASS,200,seats);
        availability.add(planeClassFirst);

        LocalDateTime ts = LocalDateTime.of(2017,11,9,14,0,0);

        Flight flight = new Flight(departureAirport,destinationAirport,ts, Duration.ofHours(3),availability);
    }

    @Test
    public void testSpecificFlight() {
        Assert.assertEquals(GlobalRegion.NORTHERN_EUROPE,Flight.getOrigin().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.SOUTHERN_EUROPE,Flight.getDestination().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",Flight.getOrigin().getName());
        Assert.assertEquals("Athens International Airport",Flight.getDestination().getName());

        Assert.assertEquals(2017,Flight.getDeparture().getYear()+1900); // wtf

        Assert.assertEquals(TravelClassType.FIRST_CLASS,Flight.getAvailability().get(0).getType());

    }
}
