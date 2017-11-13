package com.realdolmen.togethair.domain;

import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.identity.Airline;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.Country;
import com.realdolmen.togethair.domain.location.GlobalRegion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchTest {

    List<Airport> airports = new ArrayList<>();
    List<Seat> seats = new ArrayList<>();
    List<TravelClass> travelClasses = new ArrayList<>();


    @Before
    public void initialize(){
        Airport airport1 = new Airport("EBBR", "Brussels Airport", Country.BEL, GlobalRegion.EUROPE);
        Airport airport2 = new Airport("LGAV", "Athens International Airport", Country.GRC, GlobalRegion.EUROPE);
        Airport airport3 = new Airport("KJFK", "John F. Kennedy International Airport", Country.USA, GlobalRegion.AMERICAS);
        airports.add(airport1);
        airports.add(airport2);
        airports.add(airport3);

        Seat s1 = new Seat(10, 10, null, Availability.FREE);
        Seat s2 = new Seat(5, 12, null, Availability.FREE);
        Seat s3 = new Seat(56, 2, null, Availability.FREE);
        Seat s4 = new Seat(33, 82, null, Availability.FREE);
        Seat s5 = new Seat(44, 55, null, Availability.FREE);


        LocalDateTime ts = LocalDateTime.of(2017, Month.NOVEMBER, 9, 14, 0);
        //ts.valueOf("");

        Flight f = new Flight(Airline.BEL, 1, airport1, airport2, ts, Duration.ofHours(3), null);

        TravelClass bclass = new TravelClass(f, TravelClassType.BUSINESS, 100.0, seats);
        TravelClass eclass = new TravelClass(f, TravelClassType.ECONOMY, 50.0, seats);
        TravelClass fclass = new TravelClass(f, TravelClassType.FIRST_CLASS, 200.0, seats);
        travelClasses.add(bclass);
        travelClasses.add(eclass);
        travelClasses.add(fclass);

        s1.setTravelClass(bclass);
        s2.setTravelClass(bclass);
        s3.setTravelClass(eclass);
        s4.setTravelClass(eclass);
        s5.setTravelClass(fclass);

        f.setAvailability(travelClasses);


    }

    @Test
    public void testSearch() {


    }

}
