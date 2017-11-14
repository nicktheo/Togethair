package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.Country;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrajectoryTest {

    Trajectory flight;

    @Before
    public void initialize(){

        Airport departureAirport = new Airport("EBBR", "Brussels Airport", Country.BEL, GlobalRegion.EUROPE);
        Airport destinationAirport = new Airport("LGAV", "Athens International Airport", Country.GRC, GlobalRegion.EUROPE);

        flight = new Trajectory(departureAirport,destinationAirport);
    }

    @Test
    public void testFlight() {
        Assert.assertEquals(GlobalRegion.EUROPE,flight.getOrigin().getGlobalRegion());
        Assert.assertEquals(GlobalRegion.EUROPE,flight.getDestination().getGlobalRegion());
        Assert.assertEquals("Brussels Airport",flight.getOrigin().getName());
        Assert.assertEquals("Athens International Airport",flight.getDestination().getName());
    }

}
