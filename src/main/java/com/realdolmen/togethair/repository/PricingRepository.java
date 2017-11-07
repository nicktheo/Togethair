package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.pricing.FlightPricing;
import com.realdolmen.togethair.pricing.GeneralPricing;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingRepository {

    @PersistenceContext
    EntityManager em;

    public GeneralPricing getGeneralPricingByName(String name) {
        TypedQuery<GeneralPricing> query = em.createQuery("SELECT gp from GeneralPricing gp WHERE gp.name = :name",
                GeneralPricing.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public List<FlightPricing> getFlightPricingForFlight(Flight flight) {
        TypedQuery<FlightPricing> query = em.createQuery("SELECT fp FROM FlightPricing fp WHERE fp.flight = :f", FlightPricing.class);
        query.setParameter("f", flight);
        return query.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void saveGeneralPricing(GeneralPricing p) {
        em.persist(p);
    }
}
