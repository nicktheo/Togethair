package com.realdolmen.togethair.domain.booking.pricing;

import com.realdolmen.togethair.domain.booking.pricing.filters.FlightFilter;
import com.realdolmen.togethair.domain.flight.Flight;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlightPriceSetting extends PriceSetting implements FlightFilter {

    @ManyToOne
    private Flight flight;


    public FlightPriceSetting() {}

    public FlightPriceSetting(Flight flight, PriceSettingType type, double value, int priority, String name) {
        super(PriceSettingLevel.BOOKINGLINE, type, value, priority, name);
        this.flight = flight;
    }

    public FlightPriceSetting(Flight flight, PriceSettingType type, double value, int priority) {
        super(PriceSettingLevel.BOOKINGLINE, type, value, priority);
        this.flight = flight;
    }

    public FlightPriceSetting(Flight flight, PriceSettingType type, double value, String name) {
        super(PriceSettingLevel.BOOKINGLINE, type, value, name);
        this.flight = flight;
    }

    public FlightPriceSetting(Flight flight, PriceSettingType type, double value) {
        super(PriceSettingLevel.BOOKINGLINE, type, value);
        this.flight = flight;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
