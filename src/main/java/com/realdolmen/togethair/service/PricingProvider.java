package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.*;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.Type;
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

    public Bookable applyFlightPricing(Bookable<BookingLine> bookingLine) {
        List<FlightPriceSetting> fpricing = pricingRepo.getFlightPricingForFlight(bookingLine.getTickets().get(0)
                .getSeat().getTravelClass().getFlight());

        // Sort the pricings on priority
        fpricing.sort(new Comparator<FlightPriceSetting>() {
            @Override
            public int compare(FlightPriceSetting o1, FlightPriceSetting o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        for (FlightPriceSetting pricing : fpricing) {
            bookingLine = applyPricing(pricing, bookingLine);
        }
        return bookingLine;
    }

    public List<Bookable> applyBookingPricing(List<Bookable> bookingLineList, String name) {
        List<Bookable> returnBookings = new ArrayList<>();
        PriceSetting gp = pricingRepo.getGeneralPricingByName(name);

        for (Bookable b : bookingLineList){
            returnBookings.add(applyPricing(gp, b));
        }

        return returnBookings;
    }

    private Bookable applyPricing(PriceSetting pricing, Bookable bookingLine) {
        if (pricing.getType() == Type.FIXED) {
            bookingLine = new FixedPricingAdapter(bookingLine, pricing.getValue());
        }
        else if (pricing.getType() == Type.PERCENTAGE) {
            bookingLine = new PercentagePricingAdapter(bookingLine, pricing.getValue());
        }

        return bookingLine;
    }
}
