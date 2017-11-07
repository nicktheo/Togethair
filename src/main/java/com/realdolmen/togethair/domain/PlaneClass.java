package com.realdolmen.togethair.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlaneClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaneClassType planeClassType;

    @Column(nullable = false)
    private double basePrice;

    @OneToMany
    private List<Seat> seats= new ArrayList<>();

    @ManyToOne
    private SpecificFlight specificFlight;

    public PlaneClass(PlaneClassType planeClassType, double basePrice, List<Seat> seats, SpecificFlight specificFlight) {
        this.planeClassType = planeClassType;
        this.basePrice = basePrice;
        this.seats = seats;
        this.specificFlight = specificFlight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaneClassType getPlaneClassType() {
        return planeClassType;
    }

    public void setPlaneClassType(PlaneClassType planeClassType) {
        this.planeClassType = planeClassType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public SpecificFlight getSpecificFlight() {
        return specificFlight;
    }

    public void setSpecificFlight(SpecificFlight specificFlight) {
        this.specificFlight = specificFlight;
    }
}
