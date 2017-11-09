package com.realdolmen.togethair.exceptions;

import java.util.NoSuchElementException;

public class NoSuchFlightException extends NoSuchElementException {

    public NoSuchFlightException() {
        super();
    }

    public NoSuchFlightException(String message) {
        super(message);
    }
}
