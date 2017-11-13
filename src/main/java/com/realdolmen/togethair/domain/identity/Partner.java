package com.realdolmen.togethair.domain.identity;

public class Partner extends BackEndUser {

    private Airline airline;


    protected Partner() {
        super();
    }

    public Partner(String firstName, String lastName, String email, Airline airline) {
        super(firstName, lastName, email);
        this.airline = airline;
    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
