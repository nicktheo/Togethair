package com.realdolmen.togethair.exceptions;

import sun.reflect.annotation.ExceptionProxy;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
public class SeatIsTakenException extends Exception{
    public SeatIsTakenException() {
    }

    public SeatIsTakenException(String message) {
        super(message);
    }
}
