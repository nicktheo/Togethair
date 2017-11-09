package com.realdolmen.togethair.domain.flight;

import com.realdolmen.togethair.domain.location.Airport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@DiscriminatorValue("S")
public class Flight extends Trajectory {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    //private Date flightDate;
    private java.sql.Timestamp flightDate;


    @Column(nullable = false)
    private String duration; // check conversion

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> availability = new ArrayList<>();

    public Flight(Airport departureAirport, Airport destinationAirport, java.sql.Timestamp flightDate , String duration, List<TravelClass> availability) {
        super(departureAirport, destinationAirport);
        this.flightDate = flightDate;
        this.duration = duration;
        this.availability = availability;
    }

    public Flight() {
        super();
    }

    public java.sql.Timestamp getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(java.sql.Timestamp dateTime) {
        this.flightDate = dateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<TravelClass> getAvailability() {
        return availability;
    }

    public void setAvailability(List<TravelClass> availability) {
        this.availability = availability;
    }
}
