package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.booking.*;
import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.identity.Airline;
import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.service.AbstractBuilderTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingBuilderTest extends AbstractBuilderTest {

    @Test
    public void builderBuildreturnsBookable(){
        Bookable b = builder.build();

        Booking booking = (Booking) b.getBase();

        //check whether total price was set
        Assert.assertEquals(100.0, booking.getTotalPrice(), 0.00);
        //check whether customer was set
        Assert.assertNotNull(booking.getCustomer());
        //check whether tickets were set
        Assert.assertNotNull(booking.getTickets());
        //check whether Uuid was set
        Assert.assertNotNull(booking.getUuid());
        //check whether id was not set (must be done by JPA)
        Assert.assertNull(booking.getId());
    }

    @Test
    public void seatsWereSetReserved(){
        Booking booking = (Booking) builder.build().getBase();

        Assert.assertEquals(Availability.RESERVED, booking.getTickets().get(0).getSeat().getAvailability());
    }

    @Test(expected = DuplicateFlightException.class)
    public void addDuplicateFlightCausesException() throws DuplicateFlightException {
        builder.addFlight(travelClasses.get(0));
    }

    @Test(expected = DuplicatePassengerException.class)
    public void addDuplicatePassengerCausesException() throws DuplicatePassengerException {
        builder.addPassenger(passenger);
    }

    @Test(expected = DuplicateSeatException.class)
    public void addDuplicateSeatCausesException() throws DuplicateSeatException {
        builder.addSeat(s);
    }
}
