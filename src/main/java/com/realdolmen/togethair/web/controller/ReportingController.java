package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.service.BookingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
@RequestScoped
public class ReportingController {

    private long bookingId = -1;
    private Booking booking;

    @Inject
    BookingService bookingService;

    public String bookingInfo() {
        if (bookingId == -1) {
            return "somethingWentWrong";
        }
        booking = bookingService.getUnmanagedBookingById(bookingId);
        if (booking == null) {
            return "somethingWentWrong";
        }
        return "bookingInfo";
    }
}
