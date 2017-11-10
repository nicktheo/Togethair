package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.service.TravelClassService;
import com.realdolmen.togethair.web.controller.LoginController;

import javax.annotation.PostConstruct;
import javax.ejb.ObjectNotFoundException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
@SessionScoped
public class BookingBean implements Serializable{

    @Inject
    private TravelClassService travelClassService;

    private List<TravelClass> travelClasses;
    private int numberOfPassengers;

    @PostConstruct
    public void initialize(){
        travelClasses = new ArrayList<>();
        numberOfPassengers = 2;
    }

    public String addFlights(List<Long> travelClassIds, int amount) {
        try {
            this.numberOfPassengers = amount;
            for (Long id : travelClassIds) {
                travelClasses.add(travelClassService.getTravelClassById(id));
            }
            return "searchFlights.xhtml";
        } catch (ObjectNotFoundException e) {
            return "somethingWentWrong.xhtml";
        }
    }

    public List<TravelClass> getTravelClasses(){
        return travelClasses;
    }

    public int getNumberOfPassengers(){
        return numberOfPassengers;
    }
}
