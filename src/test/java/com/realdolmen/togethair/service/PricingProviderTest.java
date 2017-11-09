package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Bookable;
import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingLevel;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;
import com.realdolmen.togethair.domain.exceptions.PricingNotFoundException;
import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.exceptions.NoSuchPricingException;
import com.realdolmen.togethair.repository.PricingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PricingProviderTest {

    @Mock
    private PricingRepository pricingRepo;

    @InjectMocks
    private PricingProvider provider;

    private Flight f = new Flight();
    private List<FlightPriceSetting> pricingListPercentage = new ArrayList<>();
    private List<FlightPriceSetting> pricingListFixed = new ArrayList<>();
    private List<FlightPriceSetting> pricingListCombined = new ArrayList<>();
    private List<PersonalTicket> tickets = new ArrayList<>();
    private PriceSetting gp = new PriceSetting(PriceSettingLevel.BOOKING, PriceSettingType.PERCENTAGE, 1.20, 10, "margin");

    @Before
    public void initialize() {
        provider = new PricingProvider();

        pricingListFixed.add(new FlightPriceSetting(f, PriceSettingType.FIXED, -25.0, 20, null));
        pricingListPercentage.add(new FlightPriceSetting(f, PriceSettingType.PERCENTAGE, 0.95, 25, null));

        pricingListCombined.add(new FlightPriceSetting(f, PriceSettingType.PERCENTAGE, 0.95, 25, null));
        pricingListCombined.add(new FlightPriceSetting(f, PriceSettingType.FIXED, -25.0, 20, null));

        //ADD Price!!!!


        Seat s1 = new Seat(10, 10, null, Availability.FREE);
        Seat s2 = new Seat(10, 11, null, Availability.FREE);

        List<Seat> seats = new ArrayList<>();
        seats.add(s1);
        seats.add(s2);
        TravelClass tclass = new TravelClass(f, TravelClassType.BUSINESS, 100.0, seats);
        s1.setTravelClass(tclass);
        s2.setTravelClass(tclass);
        tickets.add(new PersonalTicket(s1, new SimplePassenger("J", "C", "123")));
        tickets.add(new PersonalTicket(s2, new SimplePassenger("N", "T", "123")));

//        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingList);
//        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(new PriceSetting(PriceSettingType.PERCENTAGE, 1.20, 10, "margin"));
//        MockitoAnnotations.initMocks(this);
        //Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListFixed);

//        Bookable b = new BookingLine(tickets);
//        Bookable bTest = provider.applyFlightPricing(b);

//        Assert.assertEquals(75.0, bTest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingFixed() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListFixed);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(75.0, bTest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingPercentage() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListPercentage);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(95.0, bTest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingCombined() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        Bookable b = new BookingLine(tickets);
        Bookable bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(71.25, bTest.getPrice(), 0.001);
    }

    // TODO Use Booking.Builder
    @Test
    public void pricingProviderAppliesBookingPricing() throws NoSuchPricingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);

        List<BookingLine> bLines = new ArrayList<>();
        bLines.add(new BookingLine(tickets));
        //Bookable b = new Booking();
        //((Booking) b).setBookingLines(bLines);
        //Bookable btest = provider.applyBookingPricing(b, "margin");

        //Assert.assertEquals(120.0, btest.getPrice(), 0.001);
    }

    // TODO Use Booking.Builder
    @Test
    public void pricingProviderAppliesFlightAndBookingPricing() throws NoSuchPricingException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        List<BookingLine> bLines = new ArrayList<>();
        BookingLine bl = new BookingLine(tickets);
        bLines.add(bl);
        //Bookable<Booking> b = new Booking();
        //((Booking) b).setBookingLines(bLines);
        //b = provider.applyFlightPricing(bl);
        //Bookable btest = provider.applyBookingPricing(b, "margin");

        //Assert.assertEquals(85.5, btest.getPrice(), 0.001);
    }

}
