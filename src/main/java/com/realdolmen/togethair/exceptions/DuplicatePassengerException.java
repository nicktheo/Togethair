package com.realdolmen.togethair.exceptions;

import javax.ejb.DuplicateKeyException;

public class DuplicatePassengerException extends DuplicateKeyException {

    public DuplicatePassengerException() {}

    public DuplicatePassengerException(String message) {
        super(message);
    }
}
