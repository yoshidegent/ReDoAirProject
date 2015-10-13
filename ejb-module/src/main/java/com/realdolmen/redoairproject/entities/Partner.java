package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Partner extends User{

    @ManyToOne
    private Airline airline;

    public Partner(String username, String password) {
        super(username, password);
    }

    public Partner() {
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

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
