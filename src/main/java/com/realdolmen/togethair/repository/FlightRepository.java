package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Flight;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.domain.location.GlobalRegion;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestScoped
public class FlightRepository {

    @PersistenceContext
    EntityManager em;

    public List<Flight> findFlightsByAirportNameDateTimesAndAmountOfFreeSeats(Airport origin, Airport destination, int amount, LocalDateTime after, LocalDateTime before) {
        TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f WHERE f.origin = :origin " +
                        "AND f.destination = :destination " +
                        "AND f.departure > :after " +
                        "AND f.departure < :before " +
                        "AND :amount <= (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.flight = f AND s.availability = :av)", Flight.class);

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
                "AND :amount <= (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.flight = f AND s.availability = :av)", Flight.class);

        return query.setParameter("origin", origin).setParameter("destination", destination)
                .setParameter("after", after).setParameter("before", before).setParameter("amount", amount)
                .setParameter("av", Availability.FREE)
                .getResultList();
    }

    public List<Flight> findAvailableFlights(Airport origin, Airport destination, LocalDate departureDate, TravelClassType travelClass, long seatCount) {
        TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f WHERE f.origin = :origin " +
                "AND f.destination = :destination " +
                "AND f.departure >= :departureAfter " +
                "AND f.departure < :departureBefore " +
                "AND :seatCount <= (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.flight = f AND s.travelClass.type = :tc AND s.availability = :av)", Flight.class);

        return query
                .setParameter("origin", origin)
                .setParameter("destination", destination)
                .setParameter("departureAfter", departureDate.atStartOfDay())
                .setParameter("departureBefore", departureDate.plusDays(1).atStartOfDay())
                .setParameter("seatCount", seatCount)
                .setParameter("tc", travelClass)
                .setParameter("av", Availability.FREE)
                .getResultList();
    }

    public List<Airport> getAllAirports() {
        TypedQuery<Airport> query = em.createQuery("SELECT a FROM Airport a", Airport.class);

        return query.getResultList();
    }
}
