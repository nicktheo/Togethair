package com.realdolmen.togethAir.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Embeddable
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private int number;
    @Column(nullable = false, length = 50)
    private String holderName;

    // FIX THIS TYPE
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false, length = 15)
    private String type; // VISA etc.

    @Column(nullable = false, length = 3)
    private int CVV;

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
}
