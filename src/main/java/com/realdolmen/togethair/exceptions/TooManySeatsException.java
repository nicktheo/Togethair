package com.realdolmen.togethair.exceptions;

public class TooManySeatsException extends IllegalArgumentException {

    public TooManySeatsException() {}

    public TooManySeatsException(String message) {
        super(message);
    }
}
