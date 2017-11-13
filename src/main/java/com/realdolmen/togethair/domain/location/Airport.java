package com.realdolmen.togethair.domain.location;

import javax.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String icaoCode;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private GlobalRegion globalRegion;


    protected Airport() {}

    public Airport(String icaoCode, String name, Country country, GlobalRegion globalRegion) {
        this.icaoCode = icaoCode;
        this.name = name;
        this.country = country;
        this.globalRegion = globalRegion;
    }

    public Airport(String icaoCode) {
        this.icaoCode = icaoCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icao) {
        this.icaoCode = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public GlobalRegion getGlobalRegion() {
        return globalRegion;
    }

    public void setGlobalRegion(GlobalRegion globalRegion) {
        this.globalRegion = globalRegion;
    }


    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        return icaoCode.equals(airport.icaoCode);
    }

    @Override
    public int hashCode() {
        return icaoCode.hashCode();
    }
}
