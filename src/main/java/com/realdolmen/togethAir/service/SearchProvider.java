package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.flight.Seat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchProvider {

    public List<Seat> searchFlight(int numberOfSeats, TravelClassType travellingClass, Object departure, Object destination, Date date) {

        //sort out airports or global regions as parameters (generics?)

        List<Seat> availableSeats = new ArrayList<>(); //list of available seats to return

        return null;

    }

}
