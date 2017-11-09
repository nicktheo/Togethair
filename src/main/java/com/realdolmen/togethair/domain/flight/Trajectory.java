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
    private Airport origin;
    @ManyToOne
    private Airport destination;


    public Trajectory() {}

    public Trajectory(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
