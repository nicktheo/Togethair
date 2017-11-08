package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.Type;
import com.realdolmen.togethair.repository.PricingRepository;
import org.junit.Before;
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

    private Trajectory f = new Trajectory();
    private List<FlightPriceSetting> pricingList = new ArrayList<>();
    private List<PersonalTicket> tickets = new ArrayList<>();

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
