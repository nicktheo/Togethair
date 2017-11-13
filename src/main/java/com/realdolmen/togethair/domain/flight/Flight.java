package com.realdolmen.togethair.domain.flight;

import com.realdolmen.togethair.domain.location.Airport;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight extends Trajectory {

    @Column(nullable = false)
    private LocalDateTime departure;
    @Column(nullable = false)
    private Duration duration;

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> availability = new ArrayList<>();


    protected Flight() {
        super();
    }

    public Flight(Airport origin, Airport destination, LocalDateTime departure, Duration duration, List<TravelClass> availability) {
        super(origin, destination);
        this.departure = departure;
        this.duration = duration;
        this.availability = availability;
    }


    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime dateTime) {
        this.departure = dateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<TravelClass> getAvailability() {
        return availability;
    }

    public void setAvailability(List<TravelClass> availability) {
        this.availability = availability;
    }
}
