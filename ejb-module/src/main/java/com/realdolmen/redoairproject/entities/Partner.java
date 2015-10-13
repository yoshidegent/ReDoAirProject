package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Partner extends User {


    /**
     * Class fields
     */
    @ManyToOne
    private Airline airline;

    /**
     * Constructor
     */
    public Partner(String username, String password) {
        super(username, password);
    }

    public Partner() {
        super("", "");
    }


    /**
     * Bussiness Methods
     */


    /**
     * Getters & Setters
     */

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
