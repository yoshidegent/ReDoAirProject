package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {

    /**
     * Class fields
     */
    @ManyToMany
    private List<Flight> flightList;
    private String hotel;
    private int priceHotelPerNightPerPerson;
    private int numberOfNights;
    @ManyToOne
    private Airport endDestination;


    /**
     * Constructor
     */
    public Trip() {
    }


    public Trip(Long id, List<Flight> flightList, String hotel, int priceHotelPerNightPerPerson, int numberOfNights, Airport endDestination) {
        super(id);
        this.flightList = flightList;
        this.hotel = hotel;
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
        this.numberOfNights = numberOfNights;
        this.endDestination = endDestination;
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

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getPriceHotelPerNightPerPerson() {
        return priceHotelPerNightPerPerson;
    }

    public void setPriceHotelPerNightPerPerson(int priceHotelPerNightPerPerson) {
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Airport getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(Airport endDestination) {
        this.endDestination = endDestination;
    }
}
