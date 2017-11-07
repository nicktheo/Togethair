package com.realdolmen.domain;

import com.realdolmen.togethAir.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class BookingLineTest {

    private BookingLine bookingLine;
    private PlaneClass pClass = new PlaneClass();

    @Before
    public void initialize() {

        List<PersonalTicket> tickets = new ArrayList<>();
        tickets.add(new PersonalTicket("Jeroen", "Cloetens", "123456789", new Seat(10,10, Availability.FREE, pclass)));
        tickets.add(new PersonalTicket("Jeroen", "Cloetens", "123456789", new Seat(10,11,Availability.FREE, pclass)));
        this.bookingLine = new BookingLine();
        bookingLine.setTickets(tickets);
    }

    @Test
    public void bookingLineGetPriceReturnsCorrectPrice() {
        Assert.assertEquals(pClass.getPrice() * 2, bookingLine.getPrice(), 0.0);
    }
}
