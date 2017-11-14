package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.identity.Customer;

import javax.persistence.*;

public class UserRepository {

    @PersistenceContext
    EntityManager em;


    public Customer findCustomerByEmail(String email) {
        TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c " +
                "WHERE c.email = :email", Customer.class);

        try {
            return query
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
