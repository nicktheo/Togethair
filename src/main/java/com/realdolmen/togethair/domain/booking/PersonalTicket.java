package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PersonalTicket {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String passportNumber;

    @OneToOne
    private Seat seat;


    public PersonalTicket() {}

    public PersonalTicket(Seat seat, String firstName, String lastName, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.seat = seat;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNr) {
        this.passportNumber = passportNr;
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
