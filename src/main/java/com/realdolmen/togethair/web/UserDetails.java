package com.realdolmen.togethair.web;

import com.realdolmen.togethair.domain.identity.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserDetails implements Serializable {

    private Customer user;


    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }


    public boolean isLoggedIn(){
        return user != null;
    }

    public void logOut(){
        user = null;
    }
}
