package com.realdolmen.togethair.domain.identity;

public class SimplePassenger implements Passenger {

    private String firstName;
    private String lastName;
    private String passportNumber;


    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
