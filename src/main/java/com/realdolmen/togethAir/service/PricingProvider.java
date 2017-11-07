package com.realdolmen.togethAir.service;

import com.realdolmen.togethAir.domain.BookingLinePricingFixed;
import com.realdolmen.togethAir.domain.BookingLinePricingPercentage;
import com.realdolmen.togethAir.domain.IBookingLine;
import com.realdolmen.togethAir.pricing.FlightPricing;
import com.realdolmen.togethAir.pricing.GeneralPricing;
import com.realdolmen.togethAir.pricing.Type;
import com.realdolmen.togethAir.repository.PricingRepository;

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

    public IBookingLine applyFlightPricing(IBookingLine bookingLine) {
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

    public List<IBookingLine> applyBookingPricing(List<IBookingLine> bookingLineList, String name) {
        List<IBookingLine> returnBookings = new ArrayList<>();
        GeneralPricing gp = pricingRepo.getGeneralPricingByName(name);

        for (IBookingLine b : bookingLineList){
            returnBookings.add(applyPricing(gp, b));
        }

        return returnBookings;
    }

    private IBookingLine applyPricing(GeneralPricing pricing, IBookingLine bookingLine) {
        if (pricing.getType() == Type.FIXED) {
            bookingLine = new BookingLinePricingFixed(pricing.getValue(), bookingLine);
        }
        else if (pricing.getType() == Type.PERCENTAGE) {
            bookingLine = new BookingLinePricingPercentage(pricing.getValue(), bookingLine);
        }

        return bookingLine;
    }
}
