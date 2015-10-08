package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger extends User {

    /**
     * Class fields
     */
    @ManyToMany
    private List<Trip> tripList = new ArrayList<>();


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
