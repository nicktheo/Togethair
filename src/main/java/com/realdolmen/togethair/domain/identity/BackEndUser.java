package com.realdolmen.togethair.domain.identity;

public abstract class BackEndUser extends User {

    public BackEndUser() {
        super();
    }

    public BackEndUser(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
}
