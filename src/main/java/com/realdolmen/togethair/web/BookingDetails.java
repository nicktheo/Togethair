package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.flight.TravelClass;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@FlowScoped("search")
public class BookingDetails implements Serializable {

    private List<TravelClass> flights = new ArrayList<>();
    private int passengerCount;


    public List<TravelClass> getFlights(){
        return flights;
    }

    public void setFlights(List<TravelClass> flights) {
        this.flights = flights;
    }

    public void addFlight(TravelClass travelClass) {
        this.flights.add(travelClass);
    }

    public int getPassengerCount(){
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount){
        this.passengerCount = passengerCount;
    }
}
