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

    private String firstName;
    private String lastName;
    private String emailAddress;



    /**
     * Constructor
     */


    public Passenger(String username, String password) {
        super(username, password);
    }

    public Passenger() {
        super("","");
    }



    /**
     * Bussiness Methods
     */


    /**
     * Getters & Setters
     */

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
