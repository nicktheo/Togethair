package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.identity.Customer;

import javax.persistence.*;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
public class CustomerRepository {

    @PersistenceContext
    EntityManager em;


    public Customer findCustomerByEmail(String email) {
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c WHERE c.email = :email", Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
