package com.realdolmen.togethair.domain.booking;

import java.util.List;

public abstract class PricingAdapter<T extends Bookable> implements Bookable<T> {

    private Bookable<T> bookable;
    private double value;


    public PricingAdapter(Bookable<T> bookable, double value) {
        this.bookable = bookable;
        this.value = value;
    }

    public PricingAdapter() {
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
    public List<PersonalTicket> getTickets() {
        return bookable.getTickets();
    }
}
