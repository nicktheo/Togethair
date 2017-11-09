package com.realdolmen.togethair.exceptions;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
public class NotEnoughSeatsException extends Exception{
    public NotEnoughSeatsException() {
    }

    public NotEnoughSeatsException(String message) {
        super(message);
    }
}
