package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Bookable;
import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.Type;
import com.realdolmen.togethair.repository.PricingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingProviderTest {

    @Mock
    private PricingRepository pricingRepo;

    @InjectMocks
    private PricingProvider provider;

    private Trajectory f = new Trajectory();
    private List<FlightPriceSetting> pricingList = new ArrayList<>();
    private List<PersonalTicket> tickets = new ArrayList<>();
    private PriceSetting gp = new PriceSetting(Type.PERCENTAGE, 1.20, 10, "margin");

    @Before
    public void initialize() {
        provider = new PricingProvider();

        pricingList.add(new FlightPriceSetting(Type.FIXED, -25.0, 20, null, f));
        pricingList.add(new FlightPriceSetting(Type.PERCENTAGE, 0.95, 25, null, f));
        //ADD Price!!!!
        Seat s1 = new Seat(10, 10, Availability.FREE, new TravelClass());
        Seat s2 = new Seat(10, 11, Availability.FREE, new TravelClass());
        tickets.add(new PersonalTicket("J", "C", "123", s1));
        tickets.add(new PersonalTicket("N", "T", "123", s2));

        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingList);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(new PriceSetting(Type.PERCENTAGE, 1.20, 10, "margin"));
        MockitoAnnotations.initMocks(this);
        //Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListFixed);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(75.0, bTest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingPercentage() {
        MockitoAnnotations.initMocks(this);
        //Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListPercentage);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(bTest.getPrice(), 95.0, 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingCombined() {
        MockitoAnnotations.initMocks(this);
        //Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(bTest.getPrice(), 71.25, 0.001);
    }

    @Test
    public void pricingProviderAppliesBookingPricing(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);

        List<Bookable> bLines = new ArrayList<>();
        bLines.add(new BookingLine(tickets));
        //Bookable b = new Booking(bLines, null);
        //Bookable btest = provider.applyBookingPricing(b, "margin");

        //Assert.assertEquals(120.0, btest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightAndBookingPricing() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);
        //Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        List<Bookable> bLines = new ArrayList<>();
        Bookable bl = new BookingLine(tickets);
        bLines.add(bl);
        //Bookable b = new Booking(bLines, null);
        //b = provider.applyFlightPricing(bl);
        //Bookable btest = provider.applyBookingPricing(b, "margin");

        //Assert.assertEquals(85.5, btest.getPrice(), 0.001);
    }

}
