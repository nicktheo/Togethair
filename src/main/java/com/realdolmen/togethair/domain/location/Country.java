package com.realdolmen.togethair.domain.location;

public enum Country {

    ARG("Argentina"),
    BEL("Belgium"),
    BRA("Brazil"),
    CHN("China"),
    DEU("Germany"),
    DOM("Dominican Republic"),
    ESP("Spain"),
    ETH("Ethiopia"),
    GRC("Greece"),
    IDN("Indonesia"),
    ITA("Italy"),
    JPN("Japan"),
    LVA("Latvia"),
    MEX("Mexico"),
    PHL("Philippines"),
    POL("Poland"),
    RUS("Russia"),
    SWE("Sweden"),
    THA("Thailand"),
    TJK("Tajikistan"),
    USA("United States of America"),
    VEN("Venezuela");


    private final String name;


    Country(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
