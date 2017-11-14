package com.realdolmen.togethair.exceptions;

public class NotEnoughSeatsException extends IllegalStateException {

    public NotEnoughSeatsException() {
        super();
    }

    public NotEnoughSeatsException(String message) {
        super(message);
    }

    public NotEnoughSeatsException(Exception e) {
        super(e);
    }
}
