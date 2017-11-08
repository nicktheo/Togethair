package com.realdolmen.togethair.domain.booking.pricing.filters;

import com.realdolmen.togethair.domain.flight.Flight;

public interface FlightFilter {
    Flight getFlight();
    void setFlight(Flight flight);
}
