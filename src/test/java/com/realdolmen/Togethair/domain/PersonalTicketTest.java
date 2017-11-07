package com.realdolmen.togethAir.domain;

import com.realdolmen.togethAir.domain.Availability;
import com.realdolmen.togethAir.domain.PersonalTicket;
import com.realdolmen.togethAir.domain.PlaneClass;
import com.realdolmen.togethAir.domain.Seat;
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
        Seat seat = new Seat(5, 10, Availability.FREE, new PlaneClass());
        this.pTicket = new PersonalTicket("Jeroen", "Cloetens", "1234567890", seat);
    }

    @Test
    public void getPricereturnsSeatPrice() {
        Assert.assertEquals(500.0, pTicket.getPrice(), 0.0);
    }
}
