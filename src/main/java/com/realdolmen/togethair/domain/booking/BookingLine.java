package com.realdolmen.togethair.domain.booking;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookingLine implements Bookable<BookingLine> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<PersonalTicket> tickets = new ArrayList<>();

    @Override
    public double getPrice() {
        double price = 0;
        for(PersonalTicket ticket: tickets){
            price = ticket.getPrice();
        }
        return price;
    }

    public BookingLine getBase() {
        return this;
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

    public BookingLine(List<PersonalTicket> tickets) {
        this.tickets = tickets;
    }

    public BookingLine() {}
}