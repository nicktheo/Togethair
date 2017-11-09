package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Passenger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PersonalTicket {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private Passenger passenger;
    @OneToOne
    private Seat seat;


    public PersonalTicket() {}

    public PersonalTicket(Seat seat, Passenger passenger) {
        this.seat = seat;
        this.passenger = passenger;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
