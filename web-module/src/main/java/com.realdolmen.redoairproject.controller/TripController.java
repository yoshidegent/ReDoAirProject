package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Booking;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.BookingRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TripController implements Serializable {
    private Trip trip = new Trip();
    private String cardNumber;
    private String expiryDate;
    private int numberOfPassengers;

    @Inject
    private TripRepository tripRepository;

    @Inject
    private BookingRepository bookingRepository;


    public Trip retrieveTripById(long id)  {
        return tripRepository.findById(id);
    }

    public String bookTrip(long id)
    {
        List<Flight> flightList = trip.getFlightList();
        for (Flight flight : flightList) {
            flight.setSeatsAvailable(flight.getSeatsAvailable() - numberOfPassengers);
        }

        Booking booking = new Booking();
        booking.setNumberOfPassengers(numberOfPassengers);
        booking.setCreditCardNumber(cardNumber);
        booking.setExpiryDate(expiryDate);
        booking.setTrip(trip);
        bookingRepository.createOrUpdate(booking);

        return "tripconfirmation";
    }


    public double calculateTotalPriceForTrip(int count) {
        return trip.calculateTotalPrice(count);
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

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
