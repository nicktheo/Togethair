package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.repository.BookingRepository;

import javax.ejb.ObjectNotFoundException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    public Booking persistBooking(Booking.Builder bookingBuilder, int numberOfPassengers) throws DuplicateSeatException, ObjectNotFoundException {
        return bookingRepository.persistBooking(bookingBuilder);
    }

    public Booking getBookingByUUID(String uuid) {
        return bookingRepository.getBookingByUUID(uuid);
    }
}
