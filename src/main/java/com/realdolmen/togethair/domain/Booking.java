package com.realdolmen.togethair.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking implements IPricing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private double amount;

    @OneToMany
    @JoinColumn(name = "bookinglines_fk")
    private List<BookingLine> bookingLines = new ArrayList<>();

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<BookingLine> getPersonalTickets() {
        return bookingLines;
    }

    public void setPersonalTickets(List<BookingLine> bookingLines) {
        this.bookingLines = bookingLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (BookingLine b : bookingLines) {
            price += b.getPrice();
        }
        return price;
    }

    @Override
    public List<PersonalTicket> getTickets() {
        List<PersonalTicket> tickets = new ArrayList<>();
        for (BookingLine b : bookingLines) {
            tickets.addAll(b.getTickets());
        }
        return tickets;
    }
}
