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

    private int row;
    private int column;
    @Enumerated(EnumType.STRING)
    private Availability available;
    @ManyToOne
    private PlaneClass planeClass;

    public Seat(int row, int column, Availability available, PlaneClass planeClass) {
        this.row = row;
        this.column = column;
        this.available = available;
        this.planeClass = planeClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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
