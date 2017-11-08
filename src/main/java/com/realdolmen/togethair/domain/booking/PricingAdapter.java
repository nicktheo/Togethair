package com.realdolmen.togethair.domain.booking;

public abstract class PricingAdapter<T extends Bookable> implements Bookable<T> {
    private T bookable;

    public PricingAdapter(T bookable) {
        this.bookable = bookable;
    }

    public Bookable getBookable() {
        return bookable;
    }

    public void setBookable(T bookable) {
        this.bookable = bookable;
    }

    public Bookable getBase() {
        return bookable.getBase();
    }
}
