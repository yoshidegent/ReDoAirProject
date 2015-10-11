package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class TripController implements Serializable {
    private Trip trip = new Trip();
    private String cardNumber;
    private String expiryDate;
    private int numberOfPassengers = 1;

    @Inject
    private TripRepository tripRepository;

    public Trip retrieveTripById(long id)  {
        this.trip = tripRepository.findById(id);
        return trip;
    }

    public String bookTrip(long id)
    {
        return "tripconfirmation";
    }

    public double calculateTotalPriceForTrip() {
        return trip.calculateTotalPrice(numberOfPassengers);
    }


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int  getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
