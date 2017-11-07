package com.realdolmen.togethair.domain;

import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class BookingLinePricingPercentage implements IPricing{
    private double value;
    private IPricing bookingLine;

    public BookingLinePricingPercentage(double value, IPricing bookingLine) {
        this.value = value;
        this.bookingLine = bookingLine;
    }

    @Override
    public double getPrice() {
        return bookingLine.getPrice() * value;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return bookingLine.getTickets();
    }
}
