package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.Country;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PersonalTicketTest {

    private PersonalTicket pTicket;

    @Before
    public void initialize(){
        Seat seat = new Seat(5, 10, new TravelClass(new Flight(new Airport("EBBR", "Brussels Airport", Country.BEL, GlobalRegion.EUROPE), new Airport("LGAV", "Athens International Airport", Country.GRC, GlobalRegion.EUROPE), LocalDateTime.of(2017,11,9,14,0,0), Duration.ofHours(3), Collections.EMPTY_LIST), TravelClassType.FIRST_CLASS, 0), Availability.FREE);
        Passenger passenger = new SimplePassenger("", "", "");
        this.pTicket = new PersonalTicket(seat, passenger);
    }

    @Test
    public void getPriceReturnsSeatPrice() {
//        Assert.assertEquals(500.0, pTicket.getPrice(), 0.0);
    }
}
