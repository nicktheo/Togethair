package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatIsTakenException;
import com.realdolmen.togethair.service.SeatService;

import javax.ejb.ObjectNotFoundException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped

public class BookingRepository {

    @PersistenceContext
    EntityManager em;
    @Inject
    SeatService seatService;

    @Transactional
    public Booking getUnmanagedBookingWithId(long id) {
        Booking b =  em.find(Booking.class, id);
        return b;
    }

    @Transactional(rollbackOn = {SeatIsTakenException.class})
    public Booking persistBooking(Booking.Builder builder, int amount) throws SeatIsTakenException, ObjectNotFoundException, DuplicateSeatException {
        List<Seat> seats = new ArrayList<>();
//        em.getTransaction().begin();
        for(TravelClass travelclass : builder.getFlights()) {
            seats = seatService.getFreeSeatsPessimisticLock(travelclass);
            if (seats.size() < amount) {
                throw new SeatIsTakenException();
            }
            else{
                for (int i = 0; i < amount; i++) {
                    builder.addSeat(seats.get(i));
                }
            }
        }
        Booking b = builder.build().getBase();
        em.persist(b);
        em.flush();
//        em.getTransaction().commit();

        return b;
    }

}
