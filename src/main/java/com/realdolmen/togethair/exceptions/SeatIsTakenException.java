package com.realdolmen.togethair.exceptions;

public class SeatIsTakenException extends IllegalStateException {

    public SeatIsTakenException() {
        super();
    }

    public SeatIsTakenException(String message) {
        super(message);
    }
}
