package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.BookingLinePricingFixed;
import com.realdolmen.togethair.domain.BookingLinePricingPercentage;

import com.realdolmen.togethair.domain.IPricing;
import com.realdolmen.togethair.pricing.FlightPricing;
import com.realdolmen.togethair.pricing.GeneralPricing;
import com.realdolmen.togethair.pricing.Type;
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

        for (FlightPricing pricing : fpricing) {
            bookingLine = applyPricing(pricing, bookingLine);
        }
        return bookingLine;
    }

    public List<IPricing> applyBookingPricing(List<IPricing> bookingLineList, String name) {
        List<IPricing> returnBookings = new ArrayList<>();
        GeneralPricing gp = pricingRepo.getGeneralPricingByName(name);

        for (IPricing b : bookingLineList){
            returnBookings.add(applyPricing(gp, b));
        }

        return returnBookings;
    }

    private IPricing applyPricing(GeneralPricing pricing, IPricing bookingLine) {
        if (pricing.getType() == Type.FIXED) {
            bookingLine = new BookingLinePricingFixed(pricing.getValue(), bookingLine);
        }
        else if (pricing.getType() == Type.PERCENTAGE) {
            bookingLine = new BookingLinePricingPercentage(pricing.getValue(), bookingLine);
        }

        return bookingLine;
    }
}
