package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.service.CustomerService;
import com.realdolmen.togethair.web.LoginBean;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@Named
//@FlowScoped("login")
@SessionScoped
public class LoginController implements Serializable{

    @Inject
    private CustomerService customerService;

    @Inject
    private LoginBean loginBean;

    private String email;
    private String password;


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


    public String login(){
        if (loginBean.isLoggedIn()) {
            return "book";
        }
        Customer customer = customerService.logIn(email, password);
        if (customer != null) {
            return "book";
        }
        else {
            FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage("Username or password is incorrect"));
            return "login";
        }
    }

}
