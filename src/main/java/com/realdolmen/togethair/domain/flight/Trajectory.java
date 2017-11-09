package com.realdolmen.togethair.domain.flight;

import com.realdolmen.togethair.domain.location.Airport;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Trajectory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Airport departure;
    @ManyToOne
    private Airport destination;


    public Trajectory() {}

    public Trajectory(Airport departure, Airport destination) {
        this.departure = departure;
        this.destination = destination;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departureAirport) {
        this.departure = departureAirport;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destinationAirport) {
        this.destination = destinationAirport;
    }
}
