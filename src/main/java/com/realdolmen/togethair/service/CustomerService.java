package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CustomerService {

    @Inject
    private UserRepository userRepository;

    public Customer logIn(String email, String password) {
        if (!password.equals("customer"))
            return null;

        return userRepository.findCustomerByEmail(email);
    }
}
