package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.service.TravelClassService;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BookingBean implements Serializable{

    private List<TravelClass> travelClasses = new ArrayList<>();
    private int passengerCount = 1;


    public List<TravelClass> getTravelClasses(){
        return travelClasses;
    }

    public int getPassengerCount(){
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }


    public void addFlights(List<TravelClass> travelClasses) {
        this.travelClasses.addAll(travelClasses);
    }

    public void addFlight(TravelClass travelClass) {
        this.travelClasses.add(travelClass);
    }
}
