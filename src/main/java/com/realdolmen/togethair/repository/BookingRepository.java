package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.NoSuchPricingException;
import com.realdolmen.togethair.exceptions.NotEnoughSeatsException;
import com.realdolmen.togethair.exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.service.SeatService;

import javax.ejb.ObjectNotFoundException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class BookingRepository {

    @PersistenceContext
    EntityManager em;

    @Inject
    FlightRepository flightRepository;


    @Transactional
    public Booking getBookingByUUID(String uuid) {
        TypedQuery<Booking> query = em.createQuery("SELECT b from Booking b " +
                        "WHERE b.uuid = :uuid",
                Booking.class);

        try {
            return query
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchPricingException(e);
        }
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
