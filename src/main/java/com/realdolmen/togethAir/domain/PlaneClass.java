package com.realdolmen.togethAir.domain;

import com.realdolmen.togethAir.domain.PlaneClassType;

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
    private int basePrice;

    @OneToMany
    private List<Seat> seats= new ArrayList<>();

    @ManyToOne
    private SpecificFlight specificFlight;






}
