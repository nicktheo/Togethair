package com.realdolmen.togethair.domain.booking;

import java.util.List;

public abstract class PricingAdapter<T extends Bookable> implements Bookable<T> {

    private Bookable<T> bookable;
    private double value;


    public PricingAdapter(Bookable<T> bookable, double value) {
        this.bookable = bookable;
        this.value = value;
    }

    public PricingAdapter(double value) {
        this.value = value;
    }


    public Bookable<T> getBookable() {
        return bookable;
    }

    public void setBookable(Bookable<T> bookable) {
        this.bookable = bookable;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public T getBase() {
        return bookable.getBase();
    }

    @Override
    public PricingAdapter<T> setBase(T base) throws IllegalStateException {
        if (bookable == null)
            bookable = base;
        else
            bookable.setBase(base);

        return this;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return bookable.getTickets();
    }
}
