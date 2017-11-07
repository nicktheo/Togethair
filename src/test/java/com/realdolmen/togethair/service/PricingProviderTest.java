package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.*;
import com.realdolmen.togethair.domain.pricing.FlightPricing;
import com.realdolmen.togethair.domain.pricing.GeneralPricing;
import com.realdolmen.togethair.domain.pricing.Type;
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

    private SpecificFlight f;
    private List<FlightPricing> pricingListFixed = new ArrayList<>();
    private List<FlightPricing> pricingListPercentage = new ArrayList<>();
    private List<FlightPricing> pricingListCombined = new ArrayList<>();
    private List<PersonalTicket> tickets = new ArrayList<>();
    private GeneralPricing gp = new GeneralPricing(Type.PERCENTAGE, 1.20, 10, "margin");

    @Before
    public void initialize() {
        provider = new PricingProvider();

        Seat s1 = new Seat(10, 10, Availability.FREE, null);
        Seat s2 = new Seat(10, 11, Availability.FREE, null);

        List<Seat> seats = new ArrayList<>();
        seats.add(s1);
        seats.add(s2);
        PlaneClass pc = new PlaneClass(PlaneClassType.BUSINESS, 100.0, seats, f);

        s1.setPlaneClass(pc);
        s2.setPlaneClass(pc);

        tickets.add(new PersonalTicket("J", "C", "123", s1));
        tickets.add(new PersonalTicket("N", "T", "123", s2));

        List<PlaneClass> classList = new ArrayList<>();
        classList.add(pc);
        f = new SpecificFlight(null, null, new Date(), "2h", classList);
        pc.setSpecificFlight(f);

        pricingListFixed.add(new FlightPricing(Type.FIXED, -25.0, 20, null, f));
        pricingListPercentage.add(new FlightPricing(Type.PERCENTAGE, 0.95, 25, null, f));
        pricingListCombined.add(new FlightPricing(Type.PERCENTAGE, 0.95, 25, null, f));
        pricingListCombined.add(new FlightPricing(Type.FIXED, -25.0, 20, null, f));


//        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingList);
//        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void pricingProviderAppliesFlightPricingFixed(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListFixed);

        IPricing b = new BookingLine(tickets);
        IPricing bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(75.0, bTest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingPercentage() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListPercentage);

        IPricing b = new BookingLine(tickets);
        IPricing bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(bTest.getPrice(), 95.0, 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightPricingCombined() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        IPricing b = new BookingLine(tickets);
        IPricing bTest = provider.applyFlightPricing(b);

        Assert.assertEquals(bTest.getPrice(), 71.25, 0.001);
    }

    @Test
    public void pricingProviderAppliesBookingPricing(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);

        List<IPricing> bLines = new ArrayList<>();
        bLines.add(new BookingLine(tickets));
        IPricing b = new Booking(bLines, null);
        IPricing btest = provider.applyBookingPricing(b, "margin");

        Assert.assertEquals(120.0, btest.getPrice(), 0.001);
    }

    @Test
    public void pricingProviderAppliesFlightAndBookingPricing() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(gp);
        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingListCombined);

        List<IPricing> bLines = new ArrayList<>();
        IPricing bl = new BookingLine(tickets);
        bLines.add(bl);
        IPricing b = new Booking(bLines, null);
        b = provider.applyFlightPricing(bl);
        IPricing btest = provider.applyBookingPricing(b, "margin");

        Assert.assertEquals(85.5, btest.getPrice(), 0.001);
    }

}
