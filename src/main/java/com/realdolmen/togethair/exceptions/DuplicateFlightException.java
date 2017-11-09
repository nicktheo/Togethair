package com.realdolmen.togethair.exceptions;

import javax.ejb.DuplicateKeyException;

public class DuplicateFlightException extends DuplicateKeyException {

    public DuplicateFlightException() {
        super();
    }

    public DuplicateFlightException(String message) {
        super(message);
    }
}
