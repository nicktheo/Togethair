package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.TravelClass;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
@RequestScoped
public class TravelClassRepository {

    @PersistenceContext
    EntityManager em;

    public TravelClass getTravelClassById(long id) throws ObjectNotFoundException {
        TravelClass tc = em.find(TravelClass.class, id);
        if (tc == null) {
            throw new ObjectNotFoundException("The flight was not found in the database");
        }
        return tc;
    }
}
