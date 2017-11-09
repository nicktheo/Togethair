//package com.realdolmen.togethair.domain;
//
//import com.realdolmen.togethair.domain.flight.*;
//import com.realdolmen.togethair.domain.location.Airport;
//import com.realdolmen.togethair.domain.location.GlobalRegion;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class SearchTest {
//
//    List<Airport> airports = new ArrayList<>();
//    List<Seat> seats = new ArrayList<>();
//    List<TravelClass> travelClasses = new ArrayList<>();
//
//
//    @Before
//    public void initialize(){
//        Airport airport1 = new Airport("Brussels Airport", "Belgium","BRU", GlobalRegion.NORTHERN_EUROPE);
//        Airport airport2 = new Airport("Athens International Airport", "Greece","ATH", GlobalRegion.SOUTHERN_EUROPE);
//        Airport airport3 = new Airport("New York", "USA","NYC", GlobalRegion.NORTHERN_AMERICA);
//        airports.add(airport1);
//        airports.add(airport2);
//        airports.add(airport3);
//
//        Seat s1 = new Seat(10, 10, Availability.FREE, null);
//        Seat s2 = new Seat(5, 12, Availability.FREE, null);
//        Seat s3 = new Seat(56, 2, Availability.FREE, null);
//        Seat s4 = new Seat(33, 82, Availability.FREE, null);
//        Seat s5 = new Seat(44, 55, Availability.FREE, null);
//
//
//        java.sql.Timestamp ts = new Timestamp(2017,11,9,14,0,0,0);
//        //ts.valueOf("");
//
//        Flight f = new Flight(airport1, airport2, ts, "3 hours", null);
//
//        TravelClass bclass = new TravelClass(TravelClassType.BUSINESS, 100.0, seats, f);
//        TravelClass eclass = new TravelClass(TravelClassType.ECONOMY, 50.0, seats, f);
//        TravelClass fclass = new TravelClass(TravelClassType.FIRST, 200.0, seats, f);
//        travelClasses.add(bclass);
//        travelClasses.add(eclass);
//        travelClasses.add(fclass);
//
//        s1.setTravelClass(bclass);
//        s2.setTravelClass(bclass);
//        s3.setTravelClass(eclass);
//        s4.setTravelClass(eclass);
//        s5.setTravelClass(fclass);
//
//        f.setAvailability(travelClasses);
//
//
//    }
//
//    @Test
//    public void testSearch() {
//
//
//    }
//
//}
