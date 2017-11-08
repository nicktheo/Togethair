package com.realdolmen.togethair.domain.booking.pricing;

import com.realdolmen.togethair.domain.booking.pricing.filters.FlightFilter;
import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.flight.Trajectory;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlightPriceSetting extends PriceSetting implements FlightFilter {

    @ManyToOne
    private Trajectory flight;

    public FlightPriceSetting(Type type, double value, int priority, String name, Trajectory flight) {
        super(type, value, priority, name);
        this.flight = flight;
    }

    public FlightPriceSetting() {}

    public Trajectory getFlight() {
        return flight;
    }

    public void setFlight(Trajectory flight) {
        this.flight = flight;
    }
}
