package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 6/11/2017.
 */
@Entity
public class BookingLine implements IBookingLine{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<PersonalTicket> tickets = new ArrayList<>();



    @Override
    public double getPrice() {
        double price = 0;
        for(PersonalTicket ticket: tickets){
            price = ticket.getPrice();
        }
        return price;
    }
}
