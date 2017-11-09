package com.realdolmen.togethair.exceptions;

import javax.ejb.DuplicateKeyException;

public class DuplicateFlightException extends DuplicateKeyException {

    public DuplicateFlightException() {}

    public DuplicateFlightException(String message) {
        super(message);
    }
}
