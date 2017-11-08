package com.realdolmen.togethair.domain.flight;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@DiscriminatorValue("S")
public class Flight extends Trajectory {

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateTime;

    @Column(nullable = false)
    private String duration; // check conversion

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> availability = new ArrayList<>();

    public SpecificFlight(Airport departureAirport, Airport destinationAirport, Date dateTime, String duration, List<PlaneClass> availability) {
        super(departureAirport, destinationAirport);
        this.dateTime = dateTime;
        this.duration = duration;
        this.availability = availability;
    }

    public SpecificFlight(Airport departureAirport, Airport destinationAirport, Date dateTime, String duration, List<PlaneClass> availability) {
        super(departureAirport, destinationAirport);
        this.dateTime = dateTime;
        this.duration = duration;
        this.availability = availability;
    }

    public SpecificFlight() {}

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
