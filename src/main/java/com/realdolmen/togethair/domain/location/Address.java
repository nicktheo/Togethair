package com.realdolmen.togethair.domain.location;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private int number;
    private String box;
    private String postalCode;
    private String city;
    private String country;


    public Address() {}

    public Address(String street, int number, String box, String postalCode, String city, String country) {
        this.street = street;
        this.number = number;
        this.box = box;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
