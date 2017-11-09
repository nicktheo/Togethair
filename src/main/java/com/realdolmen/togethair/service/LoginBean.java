package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.identity.BackEndUser;
import com.realdolmen.togethair.domain.identity.Customer;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.SessionScoped;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@SessionScoped
public class LoginBean {

    private String email;
    private String Password;

    private Customer customer;
    private BackEndUser admin;

    public Customer getCustomer(){
        return new Customer();
    }

    public boolean isLoggedIn(){
        return true;
    }
}