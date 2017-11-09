package com.realdolmen.togethair.exceptions;

public class TooManySeatsException extends IllegalStateException {

    public TooManySeatsException() {
        super();
    }

    public TooManySeatsException(String message) {
        super(message);
    }
}
