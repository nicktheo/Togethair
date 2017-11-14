package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Bookable;
import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingLevel;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;

import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.identity.Airline;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.Country;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import com.realdolmen.togethair.repository.PricingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PricingProviderAndBookingBuilderTest extends AbstractBuilderTest{

    @Mock
    private PricingRepository pricingRepo;

    @InjectMocks
    private PricingProvider provider;

    private List<FlightPriceSetting> pricingListPercentage = new ArrayList<>();
    private List<FlightPriceSetting> pricingListFixed = new ArrayList<>();
    private List<FlightPriceSetting> pricingListCombined = new ArrayList<>();

    private List<PersonalTicket> tickets = new ArrayList<>();
    private List<PriceSetting> generalPricings= new ArrayList<>();

    private PriceSetting generalPricing;

    @Before
    public void init() {

        provider = new PricingProvider();

        pricingListFixed.add(new FlightPriceSetting(f, PriceSettingType.FIXED, -25.0, 20, null));
        pricingListPercentage.add(new FlightPriceSetting(f, PriceSettingType.PERCENTAGE, 0.95, 25, null));

        pricingListCombined.add(new FlightPriceSetting(f, PriceSettingType.PERCENTAGE, 0.95, 25, null));
        pricingListCombined.add(new FlightPriceSetting(f, PriceSettingType.FIXED, -25.0, 20, null));

        generalPricings.add(new PriceSetting(PriceSettingLevel.BOOKINGLINE, PriceSettingType.PERCENTAGE, 1.25, 10, "margin"));

        generalPricing = new PriceSetting(PriceSettingLevel.BOOKING, PriceSettingType.PERCENTAGE, 0.8, 10, "general");
    }

    @Test
    public void pricingProviderGetFlightPricingAdaptersFixedAndMargin() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricings(f)).thenReturn(pricingListFixed);
        Mockito.when(pricingRepo.getGeneralFlightPricings()).thenReturn(generalPricings);

        builder.addPriceAdapter(provider.getFlightPricingAdapters(f), travelClasses.get(0));

        Assert.assertEquals(100.0, builder.build().getBase().getTotalPrice(), 0.001);
    }

    @Test
    public void pricingProviderGetFlightPricingAdapterPercentageAndMargin() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricings(f)).thenReturn(pricingListPercentage);
        Mockito.when(pricingRepo.getGeneralFlightPricings()).thenReturn(generalPricings);

        builder.addPriceAdapter(provider.getFlightPricingAdapters(f), travelClasses.get(0));

        Assert.assertEquals(118.75, builder.build().getBase().getTotalPrice(), 0.001);
    }

    @Test
    public void pricingProviderGetFlightPricingAdapterCombinedAndMargin() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.getFlightPricings(f)).thenReturn(pricingListCombined);
        Mockito.when(pricingRepo.getGeneralFlightPricings()).thenReturn(generalPricings);

        builder.addPriceAdapter(provider.getFlightPricingAdapters(f), travelClasses.get(0));

        Assert.assertEquals(95.0, builder.build().getBase().getTotalPrice(), 0.001);
    }

    @Test
    public void pricingProviderGetBookingPricing() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(pricingRepo.findGeneralPricing("general")).thenReturn(generalPricing);

        builder.addPriceAdapter(provider.getBookingPricingAdapter("general"));

        Assert.assertEquals(80.0, builder.build().getBase().getTotalPrice(), 0.001);
    }

}
