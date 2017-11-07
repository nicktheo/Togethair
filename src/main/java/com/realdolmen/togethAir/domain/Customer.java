package com.realdolmen.togethAir.domain;

import com.realdolmen.togethAir.domain.CreditCard;
import com.realdolmen.togethAir.domain.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Customer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String address;

    @JoinColumn(name = "creditCard_id")
    private CreditCard creditCard;

    @Override
    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
