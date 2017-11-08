package com.realdolmen.togethair.domain.booking;

import java.util.List;

public class PercentagePricingAdapter extends PricingAdapter {
    private double value;

    public PercentagePricingAdapter(Bookable bookable, double value) {
        super(bookable);
        this.value = value;
    }

    @Override
    public double getPrice() {
        return getBookable().getPrice() * value;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return getBookable().getTickets();
    }
}
