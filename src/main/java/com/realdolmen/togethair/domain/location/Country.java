package com.realdolmen.togethair.domain.location;

public enum Country {

    BEL("Belgium"),
    ESP("Spain"),
    GRC("Greece"),
    ITA("Italy"),
    JPN("Japan"),
    USA("United States of America");


    private final String name;


    Country(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
