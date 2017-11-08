package com.realdolmen.togethair.domain.flight;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.TravelClass;

import javax.persistence.*;

/**
 * Created by JCEBF12 on 6/11/2017.
 */
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int seatRow;
    private int seatColumn;
    @Enumerated(EnumType.STRING)
    private Availability available;
    @ManyToOne
    private TravelClass travelClass;

    public Seat() {
    }

    public Seat(int seatRow, int seatColumn, Availability available, TravelClass travelClass) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.available = available;
        this.travelClass = travelClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Availability getAvailable() {
        return available;
    }

    public void setAvailable(Availability available) {
        this.available = available;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public double getBasePrice() {
        return travelClass.getBasePrice();
    }
}
