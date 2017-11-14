package com.realdolmen.togethair.domain.flight;

import com.realdolmen.togethair.domain.identity.Airline;
import com.realdolmen.togethair.domain.location.Airport;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight extends Trajectory {

    @Enumerated(EnumType.STRING)
    private Airline airline;
    private int flightNumber;
    @Column(nullable = false)
    private LocalDateTime departure;
    @Column(nullable = false)
    private Duration duration;

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> availability = new ArrayList<>();


    protected Flight() {
        super();
    }

    public Flight(Airline airline, int flightNumber, Airport origin, Airport destination, LocalDateTime departure, Duration duration, List<TravelClass> availability) {
        super(origin, destination);
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.duration = duration;
        this.availability = availability;
    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int identification) {
        this.flightNumber = identification;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        return airline.equals(flight.airline) && flightNumber == flight.flightNumber && departure.equals(flight.departure);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * airline.hashCode() + flightNumber) + departure.hashCode();
    }
}
