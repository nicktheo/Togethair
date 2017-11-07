package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.BookingLinePricingFixed;
import com.realdolmen.togethair.domain.BookingLinePricingPercentage;

import com.realdolmen.togethair.domain.IPricing;
import com.realdolmen.togethair.domain.pricing.FlightPricing;
import com.realdolmen.togethair.domain.pricing.GeneralPricing;
import com.realdolmen.togethair.domain.pricing.Type;
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

    public IPricing applyFlightPricing(IPricing bookingLine) {
        List<FlightPricing> fpricing = pricingRepo.getFlightPricingForFlight(bookingLine.getTickets().get(0)
                .getSeat().getPlaneClass().getSpecificFlight());

        // Sort the pricings on priority
        fpricing.sort(new Comparator<FlightPricing>() {
            @Override
            public int compare(FlightPricing o1, FlightPricing o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        for (FlightPricing price : fpricing) {
            bookingLine = applyPricing(price, bookingLine);
        }
        return bookingLine;
    }

    public IPricing applyBookingPricing(IPricing booking, String name) {
        GeneralPricing gp = pricingRepo.getGeneralPricingByName(name);
        return applyPricing(gp, booking);
    }

    private IPricing applyPricing(GeneralPricing price, IPricing pricing) {
        if (price.getType() == Type.FIXED) {
            pricing = new BookingLinePricingFixed(price.getValue(), pricing);
        }
        else if (price.getType() == Type.PERCENTAGE) {
            pricing = new BookingLinePricingPercentage(price.getValue(), pricing);
        }

        return pricing;
    }
}
