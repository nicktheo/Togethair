package com.realdolmen.togethAir.domain;

import java.util.List;

/**
 * Created by JCEBF12 on 6/11/2017.
 */
public interface IBookingLine {
    double getPrice();
    List<PersonalTicket> getTickets();
}
