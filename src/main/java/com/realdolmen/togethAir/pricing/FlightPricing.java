package com.realdolmen.togethAir.pricing;

import javax.persistence.Entity;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
@Entity
public class FlightPricing extends GeneralPricing {

    private Flight flight;

    public FlightPricing(Type type, double value, int priority, String name, Flight flight) {
        super(type, value, priority, name);
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
