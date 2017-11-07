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
    private String number;
    @Column(nullable = false, length = 50)
    private String holderName;

    // FIX THIS TYPE
    //@Temporal(TemporalType.DATE)
    @Column(nullable = false)
    //private Date expirationDate;
    private String expirationDate;

    @Column(nullable = false, length = 15)
    private String type; // VISA etc.

    @Column(nullable = false, length = 3)
    private String CVV;

    public CreditCard(String number, String holderName, String expirationDate, String type, String CVV) {
        this.number = number;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.type = type;
        this.CVV = CVV;
    }

    public CreditCard() {}

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /*public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
*/

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
