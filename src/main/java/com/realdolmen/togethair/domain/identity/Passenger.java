package com.realdolmen.togethair.domain.identity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public interface Passenger {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPassportNumber();

    void setPassportNumber(String passportNumber);
}
