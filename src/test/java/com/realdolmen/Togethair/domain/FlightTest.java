package com.realdolmen.togethAir.domain;

import com.realdolmen.togethAir.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FlightTest {

    Flight flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
        Airport destinationAirport = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);

        flight = new Flight(departureAirport,destinationAirport);
    }

    @Test
    public void testFlight() {
        Assert.assertEquals(GlobalRegion.NORTHERN_EUROPE,flight.getDepartureAirport().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.SOUTHERN_EUROPE,flight.getDestinationAirport().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",flight.getDepartureAirport().getName());
        Assert.assertEquals("Athens International Airport",flight.getDestinationAirport().getName());
    }

}
