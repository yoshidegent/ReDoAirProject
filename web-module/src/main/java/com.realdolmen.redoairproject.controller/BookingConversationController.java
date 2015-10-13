package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Booking;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.BookingRepository;
import com.realdolmen.redoairproject.persistence.CountryRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ConversationScoped
public class BookingConversationController implements Serializable
{
    @Inject
    private Conversation conversation;

    @Inject
    private TripController tripController;

    @Inject
    private LoginController loginController;

    @Inject
    private DestinationsController destinationsController;

    @Inject
    private BookingRepository bookingRepository;
    @Inject
    private CountryRepository countryRepository;
    @Inject
    private TripRepository tripRepository;

    private Country country = new Country();
    private Booking booking = new Booking();

    private Passenger passenger;

    private String lastPage;

    public String goBackToWorldMap()
    {
        lastPage = "worldmap?faces-redirect=true";
        return lastPage;
    }

    public void startConversation() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
    }

    public String goToDestinations()
    {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String countryName = params.get("country");

        this.country = countryRepository.findCountryCodeByCountryCaseInsensitive(countryName);

        if("".equals(country.getCountryCode())) {
            return goBackToWorldMap();
        }
        else {
            destinationsController.setCountry(country);
            destinationsController.getDestinationsFromCountry();
            lastPage = "destinations?faces-redirect=true";
            return lastPage;
        }
    }

    public String goToTripDetail(Long tripId)
    {
        int numberOfPassengers = destinationsController.getNumberOfPassengers();
        this.booking.setNumberOfPassengers(numberOfPassengers);

        Trip trip = tripRepository.findById(tripId);
        this.booking.setTrip(trip);

        tripController.setTrip(trip);
        tripController.setNumberOfPassengers(numberOfPassengers);
        lastPage = "tripdetail?faces-redirect=true";
        return lastPage;
    }

    public String goToTripPayement()
    {
        if(passenger != null) {
            tripController.setTrip(this.booking.getTrip());
            tripController.setNumberOfPassengers(this.booking.getNumberOfPassengers());
            lastPage = "trippayment?faces-redirect=true";
            return lastPage;
        }
        else {
            return "login?faces-redirect=true";
        }
    }

    public String goBackToDestinations()
    {
        destinationsController.setCountry(this.country);
        lastPage = "destinations?faces-redirect=true";
        return lastPage;
    }

    public String goBackToTripDetail()
    {
        tripController.setNumberOfPassengers(this.booking.getNumberOfPassengers());
        tripController.setTrip(this.booking.getTrip());
        lastPage = "tripdetail?faces-redirect=true";
        return lastPage;
    }

    public String goToTripConfirmation()
    {
        if(passenger != null) {
            booking.setCreditCardNumber(tripController.getCardNumber());
            booking.setExpiryDate(tripController.getExpiryDate());


            return "tripconfirmation?faces-redirect=true";
        }
        else {
            return "login?faces-redirect=true";
        }
    }

    public String loginRouting()
    {
        if(lastPage != null && lastPage.startsWith("tripdetail"))
        {
            return goToTripPayement();
        }
        else
        {
            if(lastPage != null && lastPage.startsWith("worldmap"))
            {
                return goBackToWorldMap();
            }
            else
                return "";
        }
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
