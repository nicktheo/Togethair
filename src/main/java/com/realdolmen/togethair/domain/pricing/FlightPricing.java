package com.realdolmen.togethair.domain.pricing;

import com.realdolmen.togethair.domain.Flight;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
@Entity
public class FlightPricing extends GeneralPricing {

    @ManyToOne
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
