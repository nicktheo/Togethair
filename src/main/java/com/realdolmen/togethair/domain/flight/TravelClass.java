package com.realdolmen.togethair.domain.flight;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class TravelClass {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TravelClassType type;

    @ManyToOne
    private Flight flight;
    @Column(nullable = false)
    private double basePrice;

    @OneToMany(mappedBy = "travelClass")
    private List<Seat> seats;


    protected TravelClass() {}

    public TravelClass(Flight flight, TravelClassType type, double basePrice, List<Seat> seats) {
        this.type = type;
        this.basePrice = basePrice;
        this.seats = seats;
        this.flight = flight;
    }

    public TravelClass(Flight flight, TravelClassType type, double basePrice) {
        this.type = type;
        this.basePrice = basePrice;
        this.flight = flight;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelClassType getType() {
        return type;
    }

    public void setType(TravelClassType travelClassType) {
        this.type = travelClassType;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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


    public List<Seat> getSeats(Availability availability) {
        return seats.stream()
                .filter(x -> x.getAvailability() == availability)
                .collect(Collectors.toList());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelClass)) return false;

        TravelClass travelClass = (TravelClass) o;

        return flight.equals(travelClass.flight) && type.equals(travelClass.type);
    }

    @Override
    public int hashCode() {
        return 31 * flight.hashCode() + type.hashCode();
    }
}
