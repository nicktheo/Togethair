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
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private Duration duration;

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> availability = new ArrayList<>();


    public Flight() {
        super();
    }

    public Flight(Airport departure, Airport destination, LocalDateTime departureTime, Duration duration, List<TravelClass> availability) {
        super(departure, destination);
        this.dateTime = departureTime;
        this.duration = duration;
        this.availability = availability;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
