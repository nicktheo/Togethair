package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.repository.BookingRepository;

import javax.ejb.ObjectNotFoundException;
import javax.inject.Inject;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    public void persistBooking(Booking.Builder bookingBuilder, int numberOfPassengers) throws DuplicateSeatException, ObjectNotFoundException {
        bookingRepository.persistBooking(bookingBuilder, numberOfPassengers);
    }

    public Booking getUnmanagedBookingById(long id) {
        return bookingRepository.getUnmanagedBookingWithId(id);
    }
}
