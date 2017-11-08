package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.identity.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Access(AccessType.PROPERTY)
public class Booking implements Bookable<Booking> {

    private Long id;

    private double amount;

    private List<Bookable<BookingLine>> bookingLines = new ArrayList<>();

    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 50)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToMany
    public List<BookingLine> getBookingLines() {
        return bookingLines.stream()
                .map(x -> (BookingLine) x.getBase())
                .collect(Collectors.toList());
    }

    @Transient
    public Booking getBase() {
        return this;
    }

    public void setBookingLines(List<BookingLine> bookingLines) {
        this.bookingLines = new ArrayList<>();
        bookingLines.forEach(x -> this.bookingLines.add(x));
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    @Transient
    public double getPrice() {
        double price = 0;
        for (Bookable b : bookingLines) {
            price += b.getPrice();
        }
        return price;
    }

    @Override
    @Transient
    public List<PersonalTicket> getTickets() {
        List<PersonalTicket> tickets = new ArrayList<>();
        for (Bookable b : bookingLines) {
            tickets.addAll(b.getTickets());
        }
        return tickets;
    }
}
