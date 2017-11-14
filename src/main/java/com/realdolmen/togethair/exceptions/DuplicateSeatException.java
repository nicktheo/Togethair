package com.realdolmen.togethair.exceptions;

import javax.ejb.DuplicateKeyException;

public class DuplicateSeatException extends DuplicateKeyException {

    public DuplicateSeatException() {
        super();
    }

    public DuplicateSeatException(String message) {
        super(message);
    }

    public DuplicateSeatException(Exception e) {
        super(e.getMessage());
    }
}
