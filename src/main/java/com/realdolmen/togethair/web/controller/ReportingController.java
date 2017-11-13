package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.service.BookingService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
public class ReportingController {

    private Booking booking;

    @Inject
    BookingService bookingService;

    public Booking bookingInfo(long bookingId) {
        if (bookingId == -1) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            try {
                response.sendRedirect("somethingWentWrong.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        booking = bookingService.getUnmanagedBookingById(bookingId);
        if (booking == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            try {
                response.sendRedirect("somethingWentWrong.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return booking;
    }
}
