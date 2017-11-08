package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.identity.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    @GeneratedValue
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
                .map(Bookable::getBase)
                .collect(Collectors.toList());
    }

    public void setBookingLines(List<BookingLine> bookingLines) {
        this.bookingLines = new ArrayList<>();
        this.bookingLines.addAll(bookingLines);
    }

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
        return bookingLines.stream()
                .mapToDouble(Bookable::getPrice)
                .sum();
    }

    @Override
    @Transient
    public List<PersonalTicket> getTickets() {
        return bookingLines.stream()
                .map(Bookable::getTickets)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
