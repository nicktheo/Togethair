package com.realdolmen.togethair.domain.identity;

import com.realdolmen.togethair.domain.location.Address;

import javax.persistence.*;

@Entity
public class Customer extends User implements Passenger {

    private String passportNumber;
    private Address address;


    protected Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String email, String passportNumber, Address address) {
        super(firstName, lastName, email);
        this.passportNumber = passportNumber;
        this.address = address;
    }


    @Override
    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
