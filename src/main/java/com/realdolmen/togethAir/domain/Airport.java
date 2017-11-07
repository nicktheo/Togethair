package com.realdolmen.togethAir.domain;

import javax.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 50)
    private String internationalAirportCode;

    @Enumerated(EnumType.STRING)
    private GlobalRegion globalRegion;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInternationalAirportCode() {
        return internationalAirportCode;
    }

    public void setInternationalAirportCode(String internationalAirportCode) {
        this.internationalAirportCode = internationalAirportCode;
    }

    public GlobalRegion getGlobalRegion() {
        return globalRegion;
    }

    public void setGlobalRegion(GlobalRegion globalRegion) {
        this.globalRegion = globalRegion;
    }
}
