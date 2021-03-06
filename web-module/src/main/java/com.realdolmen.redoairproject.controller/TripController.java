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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Named
@ConversationScoped
public class TripController implements Serializable {
    private Trip trip = new Trip();
    private String cardNumber;
    private String expiryDate;
    private int numberOfPassengers;

    private String errorCreditCard;
    private String selectedDestination;



    @Inject
    private TripRepository tripRepository;

    @Inject
    private BookingRepository bookingRepository;



    public List<String> retrieveAllDestinations()   {
        return tripRepository.findAllDestinationNames();
    }

    public Trip retrieveTripById(long id)  {
        this.trip = tripRepository.findById(id);
        return trip;
    }

    public boolean checkCreditCardIsValid()
    {
//        List<Flight> flightList = trip.getFlightList();
//         for (Flight flight : flightList) {
//            flight.setSeatsAvailable(flight.getSeatsAvailable() - numberOfPassengers);
//        }
//
//        Booking booking = new Booking();
//        booking.setNumberOfPassengers(numberOfPassengers);
//        booking.setCreditCardNumber(cardNumber);
//        booking.setExpiryDate(expiryDate);
//        booking.setTrip(trip);
//        bookingRepository.createOrUpdate(booking);

        if(!expiryDate.isEmpty())
        {
            String[] dateValues = expiryDate.split("/");
            if(dateValues.length == 2)
            {
                try
                {
                    int month = Integer.parseInt(dateValues[0]);
                    int year = Integer.parseInt(dateValues[1]+2000);

                    LocalDate expire = LocalDate.of(year, month, 1);

                    if(LocalDate.now().isBefore(expire))
                        return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        }
        return false;
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

    public String getErrorCreditCard() {
        return errorCreditCard;
    }

    public void setErrorCreditCard(String errorCreditCard) {
        this.errorCreditCard = errorCreditCard;
    }

    public String goToDestination() {
        return "destination?faces-redirect=true";
    }

    public void setSelectedDestination(String selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public String getSelectedDestination() {
        return selectedDestination;
    }
}
