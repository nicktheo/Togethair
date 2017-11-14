package com.realdolmen.togethair.exceptions;

import java.util.NoSuchElementException;

public class NoSuchPricingException extends NoSuchElementException {

    public NoSuchPricingException() {
        super();
    }

    public NoSuchPricingException(String message) {
        super(message);
    }

    public NoSuchPricingException(Exception e) {
        super(e.getMessage());
    }
}
