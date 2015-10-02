package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;

@Entity
public class Airline extends AbstractEntity {

    /**
     * Class fields
     */
    private String name;


    /**
     * Constructor
     */

    public Airline() {
    }


    public Airline(Long id, String name) {
        super(id);
        this.name = name;
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
