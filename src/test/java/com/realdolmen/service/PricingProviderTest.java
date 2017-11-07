package com.realdolmen.service;

import com.realdolmen.togethAir.domain.*;
import com.realdolmen.togethAir.pricing.FlightPricing;
import com.realdolmen.togethAir.pricing.GeneralPricing;
import com.realdolmen.togethAir.pricing.Type;
import com.realdolmen.togethAir.repository.PricingRepository;
import com.realdolmen.togethAir.service.PricingProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingProviderTest {

    @Mock
    private PricingRepository pricingRepo;

    @InjectMocks
    private PricingProvider provider;

    private Flight f = new Flight();
    private List<FlightPricing> pricingList = new ArrayList<>();
    private List<PersonalTicket> tickets = new ArrayList<>();

    @Before
    public void initialize() {
        provider = new PricingProvider();

        pricingList.add(new FlightPricing(Type.FIXED, -25.0, 20, null, f));
        pricingList.add(new FlightPricing(Type.PERCENTAGE, 0.95, 25, null, f));
        //ADD Price!!!!
        Seat s1 = new Seat(10, 10, Availability.FREE, new PlaneClass());
        Seat s2 = new Seat(10, 11, Availability.FREE, new PlaneClass());
        tickets.add(new PersonalTicket("J", "C", "123", s1));
        tickets.add(new PersonalTicket("N", "T", "123", s2));

        Mockito.when(pricingRepo.getFlightPricingForFlight(f)).thenReturn(pricingList);
        Mockito.when(pricingRepo.getGeneralPricingByName("margin")).thenReturn(new GeneralPricing(Type.PERCENTAGE, 1.20, 10, "margin"));
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void pricingProviderAppliesFlightPricing(){
//        IBookingLine b = new BookingLine(tickets);
//        IBookingLine bTest = provider.applyFlightPricing(b);
//
//        Assert.assertEquals();
//    }

//    @Test
//    public void pricingProviderAppliesBookingPricing(){
//        provider.
//    }

}
