package com.realdolmen.togethair.domain.booking;

import java.util.List;

public interface Bookable<T extends Bookable> {

    default T getBase() {
        return (T) this;
    }

    default Bookable<T> setBase(T base) throws IllegalStateException {
        throw new IllegalStateException();
    }

    List<PersonalTicket> getTickets();

    double getPrice();
}
