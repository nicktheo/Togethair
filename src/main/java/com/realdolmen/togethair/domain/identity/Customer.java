package com.realdolmen.togethair.domain.identity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Customer extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private Address address;

//    @Override
//    public Long getId() {
//        return id;
//    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
