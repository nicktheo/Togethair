package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.TravelClass;

import javax.ejb.ObjectNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
public class TravelClassRepository {

    @PersistenceContext
    EntityManager em;

    public TravelClass getTravelClassById(long id) throws ObjectNotFoundException {
        TypedQuery<TravelClass> query = em.createQuery("SELECT t from TravelClass t WHERE t.id = :id", TravelClass.class);
        query.setParameter("id", id);
        TravelClass tc = query.getSingleResult();

        if (tc == null) {
            throw new ObjectNotFoundException("The flight was not found in the database");
        }
        return tc;
    }
}
