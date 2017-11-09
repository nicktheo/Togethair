package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrajectoryTest {

    Trajectory flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
        Airport destinationAirport = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);

        flight = new Trajectory(departureAirport,destinationAirport);
    }

    @Test
    public void testFlight() {
        Assert.assertEquals(GlobalRegion.NORTHERN_EUROPE,flight.getOrigin().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.SOUTHERN_EUROPE,flight.getDestination().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",flight.getOrigin().getName());
        Assert.assertEquals("Athens International Airport",flight.getDestination().getName());
    }

}
