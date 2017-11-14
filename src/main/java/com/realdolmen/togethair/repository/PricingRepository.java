package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.pricing.PriceSettingLevel;
import com.realdolmen.togethair.exceptions.NoSuchPricingException;
import com.realdolmen.togethair.domain.flight.Trajectory;
import com.realdolmen.togethair.domain.booking.pricing.FlightPriceSetting;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class PricingRepository {

    @PersistenceContext
    EntityManager em;


    public PriceSetting findGeneralPricing(String name) throws NoSuchPricingException {
        TypedQuery<PriceSetting> query = em.createQuery("SELECT p from PriceSetting p " +
                        "WHERE p.name = :name",
                PriceSetting.class);

        try {
            return query
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchPricingException(e);
        }
    }

    public List<FlightPriceSetting> getFlightPricings(Trajectory flight) {
        TypedQuery<FlightPriceSetting> query = em.createQuery("SELECT p FROM FlightPriceSetting p " +
                "WHERE p.flight = :flight", FlightPriceSetting.class);

        return query
                .setParameter("flight", flight)
                .getResultList();
    }

    public List<PriceSetting> getGeneralFlightPricings() {
        TypedQuery<PriceSetting> query = em.createQuery("SELECT p FROM PriceSetting p " +
                "WHERE p.level = :level AND p.flight = null", PriceSetting.class);

        return query
                .setParameter("level", PriceSettingLevel.BOOKINGLINE)
                .getResultList();
    }
}
