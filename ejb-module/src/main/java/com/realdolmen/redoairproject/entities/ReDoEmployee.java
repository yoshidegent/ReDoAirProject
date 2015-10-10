package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;

@Entity
public class ReDoEmployee extends User{
    public ReDoEmployee(String username, String password) {
        super(username, password);
    }

    public ReDoEmployee() {
        super("","");
    }

    /**
     * Class fields
     */



    /**
     * Constructor
     */



    /**
     * Bussiness Methods
     */


    /**
     * Getters & Setters
     */
}
