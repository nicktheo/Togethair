package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.pricing.PriceSettingType;
import com.realdolmen.togethair.service.AbstractPersistenceTest;
import com.realdolmen.togethair.domain.booking.pricing.PriceSetting;
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
        PriceSetting p = new PriceSetting(, PriceSettingType.PERCENTAGE, 1.10, 10, "margin");
        repo.saveGeneralPricing(p);
    }
}
