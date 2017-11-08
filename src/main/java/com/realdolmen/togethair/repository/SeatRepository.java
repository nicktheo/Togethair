package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Availability;
import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;

import javax.ejb.ObjectNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by JCEBF12 on 8/11/2017.
 */
public class SeatRepository {

    @PersistenceContext
    EntityManager em;

    public List<Seat> getTravelClassById(long id) throws ObjectNotFoundException {
        TypedQuery<Seat> query = em.createQuery("SELECT s from Seat s WHERE s.travelClass.id = :id AND s.availability = :a", Seat.class);
        query.setParameter("id", id);
        query.setParameter("a", Availability.FREE);
        return query.getResultList();
    }
}
