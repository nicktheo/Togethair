package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.domain.location.Address;
import com.realdolmen.togethair.repository.CustomerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
@RequestScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public Customer logIn(String email, String password) {
        if (!password.equals("customer")){
            return null;
        }
        return customerRepository.findCustomerByEmail(email);
    }
}
