package com.realdolmen.togethair.exceptions;

import javax.ejb.ObjectNotFoundException;

public class PricingNotFoundException extends ObjectNotFoundException{

    public PricingNotFoundException() {}

    public PricingNotFoundException(String message) {
        super(message);
    }
}
