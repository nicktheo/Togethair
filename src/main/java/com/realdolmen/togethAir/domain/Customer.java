package com.realdolmen.togethAir.domain;

import com.realdolmen.togethAir.domain.CreditCard;
import com.realdolmen.togethAir.domain.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Customer extends User {



    @Column(nullable = false, length = 60)
    private String address;

    @OneToOne
    @JoinColumn(name = "creditCard_id")
    private CreditCard creditCard;



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
