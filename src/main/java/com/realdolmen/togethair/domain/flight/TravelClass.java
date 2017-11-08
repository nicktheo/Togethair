package com.realdolmen.togethair.domain.flight;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TravelClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TravelClassType travelClassType;

    @Column(nullable = false)
    private double basePrice;

    @OneToMany(mappedBy = "travelClass")
    private List<Seat> seats= new ArrayList<>();

    @ManyToOne
    private Flight flight;

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

    public TravelClassType getTravelClassType() {
        return travelClassType;
    }

    public void setTravelClassType(TravelClassType travelClassType) {
        this.travelClassType = travelClassType;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
