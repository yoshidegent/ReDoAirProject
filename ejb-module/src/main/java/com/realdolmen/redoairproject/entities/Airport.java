package com.realdolmen.redoairproject.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Airport extends AbstractEntity {

    /**
     * Class fields
     */
    private String name;

    //@OneToOne
   // private Address address;

    /**
     * Constructor
     */
    public Airport() {
    }

    public Airport(String name/*, Address address*/) {
        this.name = name;
        //this.address = address;
    }

    /**
     * Bussiness Methods
     */


    /**
     * Getters & Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
