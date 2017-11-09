package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;
import com.realdolmen.togethair.domain.exceptions.PricingNotFoundException;
import com.realdolmen.togethair.domain.flight.Flight;
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

    public List<PricingAdapter> getFlightPricingAdapters(Flight flight) {
        List<PriceSetting> priceSettings = new ArrayList<>();
        List<PricingAdapter> bookingLineDecorators = new ArrayList<>();
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
            bookingLineDecorators.add(getPricing(pricing));
        }
        return bookingLineDecorators;
    }

    public PricingAdapter getBookingPricingAdapter(String name) throws PricingNotFoundException {
        PriceSetting gp = pricingRepo.getGeneralPricingByName(name);
        return getPricing(gp);
    }

    private PricingAdapter getPricing(PriceSetting pricing) {
        PricingAdapter decorator = null;
        if (pricing.getType() == PriceSettingType.FIXED) {
            decorator = new FixedPricingAdapter();
            decorator.setValue(pricing.getValue());
        }
        else if (pricing.getType() == PriceSettingType.PERCENTAGE) {
            decorator = new PercentagePricingAdapter();
            decorator.setValue(pricing.getValue());
        }

        return decorator;
    }
}
