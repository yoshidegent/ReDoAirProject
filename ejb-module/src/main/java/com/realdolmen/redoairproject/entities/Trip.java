package com.realdolmen.redoairproject.entities;

import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by YDEAX41 on 2/10/2015.
 */
public class Trip extends AbstractEntity {

    /**
     * Class fields
     */
    @ManyToMany
    private List<Flight> flightList;
    private String hotel;
    private int priceHotelPerNightPerPerson;
    private int numberOfNights;


    /**
     * Constructor
     */
    public Trip() {
    }


    public Trip(Long id, List<Flight> flightList, String hotel, int priceHotelPerNightPerPerson, int numberOfNights) {
        super(id);
        this.flightList = flightList;
        this.hotel = hotel;
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
        this.numberOfNights = numberOfNights;
    }

    /**
     * Bussiness Methods
     */

    public double calculateTotalPrice(int numberOfPersons) {

        return 0;
    }

    public double calculateDurationOfTrip() {
        return 0;
    }

    /**
     * Getters & Setters
     */
}
