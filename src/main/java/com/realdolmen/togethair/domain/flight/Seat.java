package com.realdolmen.togethair.domain.flight;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "seatRow")
    private int row;
    @Column(name = "seatColumn")
    private int column;

    @ManyToOne
    private TravelClass travelClass;

    @Enumerated(EnumType.STRING)
    private Availability availability;


    public Seat() {}

    public Seat(int row, int column, TravelClass travelClass, Availability availability) {
        this.row = row;
        this.column = column;
        this.availability = availability;
        this.travelClass = travelClass;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int seatRow) {
        this.row = seatRow;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int seatColumn) {
        this.column = seatColumn;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability available) {
        this.availability = available;
    }


    public double getBasePrice() {
        return travelClass.getBasePrice();
    }
}
