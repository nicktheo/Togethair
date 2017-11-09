package com.realdolmen.togethair.domain.booking;

public class FixedPricingAdapter<T extends Bookable> extends PricingAdapter<T> {

    public FixedPricingAdapter(Bookable bookable, double value) {
        super(bookable, value);
    }

    public FixedPricingAdapter(double value) {
        super(value);
    }


    @Override
    public double getPrice() {
        return getBookable().getPrice() + getValue();
    }
}
