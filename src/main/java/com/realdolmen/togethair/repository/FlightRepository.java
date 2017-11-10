package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
@RequestScoped
public class FlightRepository {

    @PersistenceContext
    EntityManager em;

    public List<Flight> findFlightsByAirportNameDateTimesAndAmountOfFreeSeats(Airport origin, Airport destination, int amount, LocalDateTime after, LocalDateTime before) {
        TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f WHERE f.origin = :origin " +
                        "AND f.destination = :destination " +
                        "AND f.departure > :after " +
                        "AND f.departure < :before " +
                        "AND :amount <= (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.flight = f AND s.availibilty = :av)", Flight.class);

        return query.setParameter("origin", origin).setParameter("destination", destination)
                .setParameter("after", after).setParameter("before", before).setParameter("amount", amount)
                .setParameter("av", Availability.FREE)
                .getResultList();
    }

    public List<Flight> findFlightsByGlobalRegionDateTimesAndAmountOfFreeSeats(GlobalRegion origin, GlobalRegion destination, int amount, LocalDateTime after, LocalDateTime before) {
        TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f WHERE f.origin.globalRegion = :origin " +
                "AND f.destination.globalRegion = :destination " +
                "AND f.departure > :after " +
                "AND f.departure < :before " +
                "AND :amount <= (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.flight = f AND s.availibilty = :av)", Flight.class);

        return query.setParameter("origin", origin).setParameter("destination", destination)
                .setParameter("after", after).setParameter("before", before).setParameter("amount", amount)
                .setParameter("av", Availability.FREE)
                .getResultList();
    }

    public List<Airport> getAllAirports() {
        TypedQuery<Airport> query = em.createQuery("SELECT a FROM Airport a", Airport.class);

        return query.getResultList();
    }
}
