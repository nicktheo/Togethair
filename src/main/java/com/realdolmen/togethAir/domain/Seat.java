package com.realdolmen.togethAir.domain;

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
    private PlaneClass planeClass;

    public Seat() {
    }

    public Seat(int seatRow, int seatColumn, Availability available, PlaneClass planeClass) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.available = available;
        this.planeClass = planeClass;
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

    public PlaneClass getPlaneClass() {
        return planeClass;
    }

    public void setPlaneClass(PlaneClass planeClass) {
        this.planeClass = planeClass;
    }

}
