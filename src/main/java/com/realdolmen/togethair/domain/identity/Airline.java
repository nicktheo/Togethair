package com.realdolmen.togethair.domain.identity;

public enum Airline {

    BEL("SN", "Brussels Airlines"),
    KLM("KL", "KLM Royal Dutch Airlines");


    private final String iataCode;
    private final String name;


    Airline(String iataCode, String name) {
        this.iataCode = iataCode;
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
