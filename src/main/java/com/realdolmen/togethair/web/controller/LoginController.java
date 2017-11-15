package com.realdolmen.togethair.web.controller;

import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.service.CustomerService;
import com.realdolmen.togethair.web.BookingDetails;
import com.realdolmen.togethair.web.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.Flow;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable{

    @Inject
    private CustomerService customerService;

    @Inject
    private UserDetails userDetails;

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
        Flow flow = FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlow();

        if (userDetails.isLoggedIn())
            return flow != null ? "passengers" : "/index.xhtml";

        Customer customer = customerService.logIn(email, password);

        if (customer != null) {
            userDetails.setUser(customer);
            return flow != null ? "passengers" : "/index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage("Username or password is incorrect"));
            return null;
        }
    }

}
