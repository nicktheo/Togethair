package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;
import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.exceptions.PricingNotFoundException;
import com.realdolmen.togethair.repository.PricingRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingProvider {

    @Inject
    private PricingRepository pricingRepo;

    public PricingAdapter getFlightPricingAdapters(Flight flight) {
        List<PriceSetting> priceSettings = new ArrayList<>();
        PricingAdapter bookingLineDecorator = null;
        priceSettings.addAll(pricingRepo.getFlightPricingForFlight(flight));
        priceSettings.addAll(pricingRepo.getGeneralFlightPricings());

        // Sort the pricings on priority
        priceSettings.sort(new Comparator<PriceSetting>() {
            @Override
            public int compare(PriceSetting o1, PriceSetting o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        for (PriceSetting pricing : priceSettings) {
            bookingLineDecorator= getPricing(pricing, bookingLineDecorator);
        }
        return bookingLineDecorator;
    }

    public PricingAdapter getBookingPricingAdapter(String name) throws PricingNotFoundException {
        PriceSetting gp = pricingRepo.getGeneralPricingByName(name);
        return getPricing(gp, null);
    }

    private PricingAdapter getPricing(PriceSetting pricing, Bookable bookable) {
        PricingAdapter decorator = null;
        if (pricing.getType() == PriceSettingType.FIXED) {
            decorator = new FixedPricingAdapter(bookable, pricing.getValue());
        }
        else if (pricing.getType() == PriceSettingType.PERCENTAGE) {
            decorator = new PercentagePricingAdapter(bookable, pricing.getValue());
        }

        return decorator;
    }
}
