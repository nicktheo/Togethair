package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.flight.TravelClassType;
import com.realdolmen.togethair.domain.location.Airport;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@RequestScoped
public class FlightRepository {

    @PersistenceContext
    EntityManager em;


    public List<TravelClass> findAvailableFlights(Airport origin, Airport destination, LocalDate departureDate, TravelClassType travelClass, long seatCount) {
        TypedQuery<TravelClass> query = em.createQuery("SELECT t FROM TravelClass t " +
                "WHERE t.type = :travelClass " +
                    "AND t.flight.origin = :origin " +
                    "AND t.flight.destination = :destination " +
                    "AND t.flight.departure >= :departureAfter " +
                    "AND t.flight.departure < :departureBefore " +
                    "AND :seatCount <= (SELECT COUNT(s) FROM Seat s " +
                        "WHERE s.travelClass = t " +
                            "AND s.availability = :availability)",
                TravelClass.class);

        return query
                .setParameter("travelClass", travelClass)
                .setParameter("origin", origin)
                .setParameter("destination", destination)
                .setParameter("departureAfter", departureDate.atStartOfDay())
                .setParameter("departureBefore", departureDate.plusDays(1).atStartOfDay())
                .setParameter("seatCount", seatCount)
                .setParameter("availability", Availability.FREE)
                .getResultList();
    }

    //@Transactional
    public List<Seat> getFreeSeats(TravelClass travelClass) {
        TypedQuery<Seat> query = em.createQuery("SELECT s from Seat s " +
                "WHERE s.travelClass = :travelClass " +
                    "AND s.availability = :availability",
                Seat.class);

        return query
                .setParameter("travelClass", travelClass)
                .setParameter("availability", Availability.FREE)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
    }

    public List<Airport> getAllAirports() {
        TypedQuery<Airport> query = em.createQuery("SELECT a FROM Airport a", Airport.class);

        return query.getResultList();
    }
}
