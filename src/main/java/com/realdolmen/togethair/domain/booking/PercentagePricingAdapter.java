package com.realdolmen.togethair.domain.booking;

public class PercentagePricingAdapter<T extends Bookable> extends PricingAdapter<T> {

    public PercentagePricingAdapter(Bookable bookable, double value) {
        super(bookable, value);
    }


    @Override
    public double getPrice() {
        return getBookable().getPrice() * getValue();
    }
}
