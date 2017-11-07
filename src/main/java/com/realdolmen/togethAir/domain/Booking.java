package com.realdolmen.togethAir.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private double price;

    @OneToMany
    @JoinColumn(name = "ticket_fk")
    private List<PersonalTicket> personalTickets= new ArrayList<>();

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PersonalTicket> getPersonalTickets() {
        return personalTickets;
    }

    public void setPersonalTickets(List<PersonalTicket> personalTickets) {
        this.personalTickets = personalTickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
