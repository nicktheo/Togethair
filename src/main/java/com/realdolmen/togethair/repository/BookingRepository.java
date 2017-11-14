package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.NotEnoughSeatsException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class BookingRepository {

    @PersistenceContext
    EntityManager em;

    @Inject
    FlightRepository flightRepository;


    public Booking getBookingByUUID(String uuid) {
        TypedQuery<Booking> query = em.createQuery("SELECT b from Booking b " +
                        "WHERE b.uuid = :uuid",
                Booking.class);

        return query
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    @Transactional(rollbackOn = {NotEnoughSeatsException.class})
    public Booking persistBooking(Booking.Builder bookingBuilder) throws NotEnoughSeatsException, DuplicateSeatException {
        int passengerCount = bookingBuilder.countPassengers();

        for(TravelClass travelclass : bookingBuilder.getFlights()) {
            List<Seat> seats = flightRepository.getFreeSeats(travelclass);

            if (seats.size() < passengerCount)
                throw new NotEnoughSeatsException();

            bookingBuilder.addSeats(seats.subList(0, passengerCount));
        }

        Booking booking = bookingBuilder.build().getBase();
        em.persist(booking);

        return booking;
    }

}
