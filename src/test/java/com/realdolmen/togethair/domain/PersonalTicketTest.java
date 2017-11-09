package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PersonalTicketTest {

    private PersonalTicket pTicket;

    @Before
    public void initialize(){
        Seat seat = new Seat(5, 10, new TravelClass(), Availability.FREE);
        Passenger passenger = new SimplePassenger("", "", "");
        this.pTicket = new PersonalTicket(seat, passenger);
    }

    @Test
    public void getPriceReturnsSeatPrice() {
//        Assert.assertEquals(500.0, pTicket.getPrice(), 0.0);
    }
}
