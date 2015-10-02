package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;

@Entity
public class Airport extends AbstractEntity {

    /**
     * Class fields
     */
    private String name;
    private String city;
    private String country;

    /**
     * Constructor
     */
    public Airport() {
    }

    public Airport(Long id, String name, String city, String country) {
        super(id);
        this.name = name;
        this.city = city;
        this.country = country;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
