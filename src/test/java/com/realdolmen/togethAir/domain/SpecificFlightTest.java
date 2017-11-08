package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecificFlightTest {

    Flight Flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
        Airport destinationAirport = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
        PlaneClass planeClassFirst;
        Seat seat = new Seat(5, 10, Availability.FREE, new PlaneClass());
        List<Seat> seats = new ArrayList<>();
        List<PlaneClass> availability = new ArrayList<>();

        seats.add(seat);

        planeClassFirst = new PlaneClass(PlaneClassType.FIRST,200,seats,specificFlight);
        availability.add(planeClassFirst);
        specificFlight = new SpecificFlight(departureAirport,destinationAirport,date,"3 hours",availability);
    }

    @Test
    public void testSpecificFlight() {
        Assert.assertEquals(GlobalRegion.NORTHERN_EUROPE,specificFlight.getDepartureAirport().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.SOUTHERN_EUROPE,specificFlight.getDestinationAirport().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",specificFlight.getDepartureAirport().getName());
        Assert.assertEquals("Athens International Airport",specificFlight.getDestinationAirport().getName());

        Assert.assertEquals(2017,specificFlight.getDateTime().getYear()+1900); // wtf

        Assert.assertEquals(PlaneClassType.FIRST,specificFlight.getAvailability().get(0).getPlaneClassType());

    }
}
