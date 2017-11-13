package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Passenger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class BookingLine implements Bookable<BookingLine> {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PersonalTicket> tickets;


    protected BookingLine() {}

    public BookingLine(List<PersonalTicket> tickets) throws IllegalArgumentException {
        setTickets(tickets);
    }

    public BookingLine(List<Passenger> passengers, List<Seat> seats) throws IllegalArgumentException {
        if (passengers.size() != seats.size())
            throw new IllegalArgumentException();

        List<PersonalTicket> tickets = new ArrayList<>();

        for (int i = 0; i < passengers.size(); i++)
            tickets.add(new PersonalTicket(seats.get(0), passengers.get(0)));

        setTickets(tickets);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<PersonalTicket> tickets) {
        if (tickets.size() == 0)
            throw new IllegalArgumentException();

        this.tickets = tickets;
    }


    public void setSeatAvailability(Availability availability) {
        tickets.stream()
                .forEach(x -> x.getSeat().setAvailability(availability));
    }


    @Override
    public double getPrice() {
        return tickets.stream()
                .mapToDouble(PersonalTicket::getPrice)
                .sum();
    }
}
