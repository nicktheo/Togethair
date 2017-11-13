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


    protected Trajectory() {}

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trajectory)) return false;

        Trajectory trajectory = (Trajectory) o;

        return origin.equals(trajectory.origin) && destination.equals(trajectory.destination);
    }

    @Override
    public int hashCode() {
        return 31 * origin.hashCode() + destination.hashCode();
    }
}
