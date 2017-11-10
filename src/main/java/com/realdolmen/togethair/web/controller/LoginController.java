package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.service.CustomerService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@Named
@SessionScoped
public class LoginController implements Serializable{

    @Inject
    private CustomerService customerService;

    private String email;
    private String password;

    private Customer customer;


    public Customer getCustomer(){
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn(){
        if(customer != null) {
            return true;
        }
        return false;
    }

    public String login(){
        customer = customerService.logIn(email, password);
        if (isLoggedIn()){
            return "index";
        }
        return "login";
    }

}
