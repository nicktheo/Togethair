package com.realdolmen.togethair.domain.identity;

import com.realdolmen.togethair.domain.location.Address;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Customer extends User implements Passenger {

    private Address address;
    private String passportNumber;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
}
