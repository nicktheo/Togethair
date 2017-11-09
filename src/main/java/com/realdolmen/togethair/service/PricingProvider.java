package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;
import com.realdolmen.togethair.exceptions.PricingNotFoundException;
import com.realdolmen.togethair.repository.PricingRepository;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingProvider {

    @Inject
    private PricingRepository pricingRepo;

    public Bookable applyFlightPricing(Bookable<BookingLine> bookingLine) {
        List<FlightPriceSetting> fpricing = pricingRepo.getFlightPricingForFlight(bookingLine.getTickets().get(0)
                .getSeat().getTravelClass().getFlight());
        List<PriceSetting> priceSettings = pricingRepo.getGeneralFlightPricings();
        priceSettings.addAll(fpricing);

        // Sort the pricings on priority
        priceSettings.sort(new Comparator<PriceSetting>() {
            @Override
            public int compare(PriceSetting o1, PriceSetting o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        for (PriceSetting pricing : priceSettings) {
            bookingLine = applyPricing(pricing, bookingLine);
        }
        return bookingLine;
    }

    public Bookable<Booking> applyBookingPricing(Bookable<Booking> booking, String name) throws PricingNotFoundException {
        PriceSetting gp = pricingRepo.getGeneralPricingByName(name);
        return applyPricing(gp, booking);
    }

    private Bookable applyPricing(PriceSetting pricing, Bookable bookingLine) {
        if (pricing.getType() == PriceSettingType.FIXED) {
            bookingLine = new FixedPricingAdapter(bookingLine, pricing.getValue());
        }
        else if (pricing.getType() == PriceSettingType.PERCENTAGE) {
            bookingLine = new PercentagePricingAdapter(bookingLine, pricing.getValue());
        }

        return bookingLine;
    }
}
