package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.service.PricingProvider;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
public class BookingBuilder {

    @Inject
    private PricingProvider pricingProvider;

    private Booking booking;
    private List<BookingLine> bookingLines;
    private List<PersonalTicket> tickets;

    private BookingLine bLine;
    private PersonalTicket ticket;

    public BookingBuilder() {
    }

    public void createBooking(Customer c) {
        this.booking = new Booking();
        booking.setCustomer(c);
    }

    public void createBookingLine() {
        this.bLine = new BookingLine();
    }

    public void createTicket(String firstname, String lastname, String passportnr, Seat seat) {
        ticket = new PersonalTicket();
       // ticket.setFirstname(firstname);
        //ticket.setLastname(lastname);
        //ticket.setPassportNr(passportnr);
        ticket.setSeat(seat);
    }

    public void addTicket() {
        tickets.add(ticket);
        ticket = null;
    }

    public void addBookingLine() {
        bLine.setTickets(tickets);
        bookingLines.add(bLine);
        bLine = null;
    }

    public void applyPricing() {
        /*
         *  Apply Flight pricing
         */
        Bookable<BookingLine> temp;
        for(int i = 0; i < bookingLines.size(); i++) {
            temp = bookingLines.get(0);
            bookingLines.set(i, (BookingLine) pricingProvider.applyFlightPricing(temp));
        }
    }

    public Booking getBooking() {
        booking.setBookingLines(bookingLines);
        booking.setTotalPrice(booking.getPrice());
        return booking;
    }
}
