package com.realdolmen.togethair.exceptions;

public class SeatAlreadyTakenException extends IllegalStateException {

    public SeatAlreadyTakenException() {
        super();
    }

    public SeatAlreadyTakenException(String message) {
        super(message);
    }

    public SeatAlreadyTakenException(Exception e) {
        super(e);
    }
}
