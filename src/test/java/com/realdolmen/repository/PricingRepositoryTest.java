package com.realdolmen.repository;

import com.realdolmen.service.AbstractPersistenceTest;
import com.realdolmen.togethAir.pricing.GeneralPricing;
import com.realdolmen.togethAir.pricing.Type;
import com.realdolmen.togethAir.repository.PricingRepository;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
public class PricingRepositoryTest extends AbstractPersistenceTest {

    private PricingRepository repo;

    @Before
    public void initializeRepository() {
        repo = new PricingRepository();
        repo.setEm(em);
    }

    @Test
    public void savePricing(){
        GeneralPricing p = new GeneralPricing(Type.PERCENTAGE, 1.10, 10, "margin");
        repo.saveGeneralPricing(p);
    }
}
