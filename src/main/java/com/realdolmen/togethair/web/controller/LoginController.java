package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.identity.BackEndUser;
import com.realdolmen.togethair.domain.identity.Customer;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.SessionScoped;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@SessionScoped
public class LoginController {

    private String email;
    private String Password;


    public Customer getCustomer(){
        throw new UnsupportedOperationException();
    }

    public boolean isLoggedIn(){
        throw new UnsupportedOperationException();
    }
}
