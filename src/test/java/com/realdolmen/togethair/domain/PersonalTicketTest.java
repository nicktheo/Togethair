package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PersonalTicketTest {

    private PersonalTicket pTicket;

    @Before
    public void initialize(){
        Seat seat = new Seat(5, 10, Availability.FREE, new TravelClass());
        this.pTicket = new PersonalTicket("Jeroen", "Cloetens", "1234567890", seat);
    }

    @Test
    public void getPricereturnsSeatPrice() {
//        Assert.assertEquals(500.0, pTicket.getPrice(), 0.0);
    }
}
