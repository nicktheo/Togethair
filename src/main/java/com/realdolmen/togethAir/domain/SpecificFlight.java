package com.realdolmen.togethAir.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@DiscriminatorValue("S")
@Table(name = "specific_flight")
public class SpecificFlight extends Flight{

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateTime;

    @Column(nullable = false)
    private String duration; // check conversion

    @OneToMany
    @JoinColumn(name = "planeClass_fk")
    private List<PlaneClass> availability = new ArrayList<>();

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<PlaneClass> getAvailability() {
        return availability;
    }

    public void setAvailability(List<PlaneClass> availability) {
        this.availability = availability;
    }
}
