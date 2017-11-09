package com.realdolmen.togethair.domain.identity;

import javax.persistence.Embeddable;

@Embeddable
public interface Passenger {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPassportNumber();

    void setPassportNumber(String passportNumber);
}
