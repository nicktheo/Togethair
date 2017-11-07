package com.realdolmen.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by JCEBF12 on 6/11/2017.
 */
@Entity
public class PersonalTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String passportNr;

    @OneToOne
    private Seat seat;

    public PersonalTicket(String firstname, String lastname, String passportNr, Seat seat) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.passportNr = passportNr;
        this.seat = seat;
    }

    //TO BE IMPLEMENTED
    public double getPrice() {
        throw new UnsupportedOperationException("This method has not been implemented yet");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassportNr() {
        return passportNr;
    }

    public void setPassportNr(String passportNr) {
        this.passportNr = passportNr;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
