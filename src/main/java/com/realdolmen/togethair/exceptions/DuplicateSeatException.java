package com.realdolmen.togethair.exceptions;

import javax.ejb.DuplicateKeyException;

public class DuplicateSeatException extends DuplicateKeyException {

    public DuplicateSeatException() {}

    public DuplicateSeatException(String message) {
        super(message);
    }
}
