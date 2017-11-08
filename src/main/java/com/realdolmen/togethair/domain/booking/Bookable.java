package com.realdolmen.togethair.domain.booking;

import java.util.List;

public interface Bookable<T extends Bookable> {

    T getBase();

    List<PersonalTicket> getTickets();

    double getPrice();
}
