package com.realdolmen.togethair.domain.booking.pricing.filters;

import com.realdolmen.togethair.domain.flight.Trajectory;

public interface FlightFilter {
    Trajectory getFlight();
    void setFlight(Trajectory flight);
}
