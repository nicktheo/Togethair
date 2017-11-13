package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Passenger;

import javax.persistence.*;

@Entity
public class PersonalTicket {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Passenger passenger;
    @OneToOne
    private Seat seat;


    protected PersonalTicket() {}

    public PersonalTicket(Seat seat, Passenger passenger) {
        this.seat = seat;
        this.passenger = passenger;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }


    public double getPrice() {
        return seat.getBasePrice();
    }
}
