package com.realdolmen.togethair.domain.booking;

import java.util.List;

public class FixedPricingAdapter extends PricingAdapter {

    private double value;

    public FixedPricingAdapter(Bookable bookable, double value) {
        super(bookable);
        this.value = value;
    }

    @Override
    public double getPrice() {
        return getBookable().getPrice() + value;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return getBookable().getTickets();
    }
}
