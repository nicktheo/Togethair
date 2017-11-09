package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.PersonalTicket;
import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.SeatIsTakenException;

import javax.ejb.ObjectNotFoundException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
public class AddSeatsAndPersistBookingTransaction {

    @PersistenceContext
    EntityManager em;
    @Inject
    SeatRepository seatRepo;

    public void persistBooking(Booking.Builder builder, int amount) throws SeatIsTakenException, ObjectNotFoundException, DuplicateSeatException {
        List<Seat> seats = new ArrayList<>();
            em.getTransaction().begin();
            for(TravelClass travelclass : builder.getFlights()) {
                seats = seatRepo.getFreeSeatsPessimisticLock(travelclass);
                if (seats.size() < amount) {
                    em.getTransaction().rollback();
                    throw new SeatIsTakenException();
                }
                else{
                    for (int i = 0; i < amount; i++) {
                        builder.addSeat(seats.get(i));
                    }
                }
            }
            em.persist(builder.build());
            em.getTransaction().commit();
    }
}
