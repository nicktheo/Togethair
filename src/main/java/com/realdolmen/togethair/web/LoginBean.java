package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.identity.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by JCEBF12 on 13/11/2017.
 */

@SessionScoped
@Named
public class LoginBean implements Serializable{

    private Customer customer;

    public boolean isLoggedIn(){
        /*if(customer != null) {
            return true;
        }*/
        return false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
