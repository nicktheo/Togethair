package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.flight.TravelClass;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BookingBean implements Serializable{

    private List<TravelClass> flights = new ArrayList<>();
    private int passengerCount = 1;


    public List<TravelClass> getFlights(){
        return flights;
    }

    public void addFlights(List<TravelClass> travelClasses) {
        this.flights.addAll(travelClasses);
    }

    public void addFlight(TravelClass travelClass) {
        this.flights.add(travelClass);
    }

    public int getPassengerCount(){
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
