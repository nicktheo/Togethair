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

    private Customer customer;
    private List<Bookable<BookingLine>> bookingLines;
    private double totalPrice;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany
    public List<BookingLine> getBookingLines() {
        return bookingLines.stream()
                .map(x -> x.getBase())
                .collect(Collectors.toList());
    }

    public void setBookingLines(List<BookingLine> bookingLines) {
        this.bookingLines = new ArrayList<>();
        bookingLines.forEach(x -> this.bookingLines.add(x));
    }

    @Column(nullable = false, length = 50)
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double amount) {
        this.totalPrice = amount;
    }


    @Override
    @Transient
    public Booking getBase() {
        return this;
    }

    @Override
    @Transient
    public double getPrice() {
        double price = 0;

        //bookingLines.stream().map(x -> x.getPrice())
        for (Bookable<BookingLine> b : bookingLines)
            price += b.getPrice();

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
