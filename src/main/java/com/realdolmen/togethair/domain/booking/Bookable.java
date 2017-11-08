package com.realdolmen.togethair.domain.booking;

import java.util.List;

public interface Bookable<T extends Bookable> {
    double getPrice();
    List<PersonalTicket> getTickets();
    Bookable getBase();
}
