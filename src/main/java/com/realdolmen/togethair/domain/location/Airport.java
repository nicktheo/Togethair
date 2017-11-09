package com.realdolmen.togethair.domain.location;

import javax.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;
    @Enumerated(EnumType.STRING)
    private GlobalRegion globalRegion;


    public Airport() {}

    public Airport(String code, String name, String country, GlobalRegion globalRegion) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.globalRegion = globalRegion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String internationalAirportCode) {
        this.code = internationalAirportCode;
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

    public GlobalRegion getGlobalRegion() {
        return globalRegion;
    }

    public void setGlobalRegion(GlobalRegion globalRegion) {
        this.globalRegion = globalRegion;
    }
}
