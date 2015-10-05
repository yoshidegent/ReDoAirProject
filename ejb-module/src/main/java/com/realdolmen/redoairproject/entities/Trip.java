package com.realdolmen.redoairproject.entities;

import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by YDEAX41 on 2/10/2015.
 */
public class Trip extends AbstractEntity {

    /**
     * Class fields
     */
    @ManyToOne
    private List<Flight> flightList;
    private String hotel;
    private int priceHotelPerNight;


    /**
     * Constructor
     */
    public Trip() {
    }


    public Trip(Long id, List<Flight> flightList, String hotel, int priceHotelPerNight) {
        super(id);
        this.flightList = flightList;
        this.hotel = hotel;
        this.priceHotelPerNight = priceHotelPerNight;
    }

    /**
     * Bussiness Methods
     */

    public double calculateTotalPrice() {

        return 0;
    }

    public double calculateDurationOfTrip() {
        return 0;
    }

    /**
     * Getters & Setters
     */
}
