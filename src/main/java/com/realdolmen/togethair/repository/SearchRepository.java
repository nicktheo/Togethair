package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.location.Airport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.*;
import java.util.*;

public class SearchRepository {

    @PersistenceContext
    EntityManager em;

    public List<Seat> locateObjectsForFlight(String numberOfSeats, String travelClassString, String departure, String destination, String dateString) {

        int numberOfSeatsToInt = Integer.parseInt(numberOfSeats);
        /*
        TypedQuery<TravelClassType> travelClassTypeQuery = em.createQuery("SELECT tc FROM TravelClassType tc WHERE tc.",TravelClass.class);
        travelClassQuery.setParameter("travelClassString", travelClassString);
        TravelClass travelClass = travelClassQuery.getSingleResult();
        */
        TypedQuery<Airport> departureQuery = em.createQuery("SELECT a FROM Airport a WHERE a.name = :departure",Airport.class);
        departureQuery.setParameter("departure",departure);
        Airport departureAirport = departureQuery.getSingleResult();
        TypedQuery<Airport> destinationQuery = em.createQuery("SELECT a FROM Airport a WHERE a.name = :destination",Airport.class);
        destinationQuery.setParameter("destination",destination);
        Airport destinationAirport = destinationQuery.getSingleResult();

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateObject;
        try {
            dateObject = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            dateObject = null;
        }
        //GregorianCalendar c = new GregorianCalendar();

        List<Seat> seats = searchFlight(numberOfSeatsToInt,travelClassString,departureAirport,destinationAirport,dateObject);
        return seats;
    }

    public List<Seat> searchFlight(int numberOfSeats, String travelClass, Object departure, Object destination, Date date) {

        //sort out airports or global regions as parameters (generics?)
        //List<Seat> availableSeats = new ArrayList<>(); //list of available seats to return

        TypedQuery<Flight> queryFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureAirport = departureAirport " +
                "AND f.destinationAirport = destinationAirport ", Flight.class); //ADD DATE ! ! !
        List<Flight> flights = queryFlights.getResultList();

        List<Flight> availableFlights = new ArrayList<>();

        for (Flight flight : flights) {
            int travelClassIndex = -1;
            //for (TravelClass travelClass1 : flight.getAvailability())
//            if (travelClass1.getTravelClassType().name().equals(travelClass) && numberOfSeats <= travelClass1.getAvailableSeats().size() ) {
//                return travelClass1.getAvailableSeats();
//            }
        }
        return null;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
