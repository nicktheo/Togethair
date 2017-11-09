package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Passenger;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookingLine implements Bookable<BookingLine> {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<PersonalTicket> tickets;


    public BookingLine() {}

    public BookingLine(List<PersonalTicket> tickets) {
        this.tickets = tickets;
    }

    public BookingLine(List<Passenger> passengers, List<Seat> seats) {
        if (passengers.size() != seats.size())
            throw new IllegalArgumentException();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<PersonalTicket> tickets) {
        this.tickets = tickets;
    }


    @Override
    public double getPrice() {
        return tickets.stream()
                .mapToDouble(PersonalTicket::getPrice)
                .sum();
    }
}
