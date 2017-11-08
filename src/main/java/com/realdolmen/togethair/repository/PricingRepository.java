package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingRepository {

    @PersistenceContext
    EntityManager em;

    public PriceSetting getGeneralPricingByName(String name) {
        TypedQuery<PriceSetting> query = em.createQuery("SELECT gp from PriceSetting gp WHERE gp.name = :name",
                PriceSetting.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public List<FlightPriceSetting> getFlightPricingForFlight(Trajectory flight) {
        TypedQuery<FlightPriceSetting> query = em.createQuery("SELECT fp FROM FlightPriceSetting fp WHERE fp.flight = :f", FlightPriceSetting.class);
        query.setParameter("f", flight);
        return query.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void saveGeneralPricing(PriceSetting p) {
        em.persist(p);
    }
}
