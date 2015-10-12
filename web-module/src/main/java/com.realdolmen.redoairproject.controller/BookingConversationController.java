package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Booking;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.CountryRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
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
    private DestinationsController destinationsController;

    @Inject
    private CountryRepository countryRepository;
    @Inject
    private TripRepository tripRepository;

    private Country country = new Country();
    private Booking booking = new Booking();

    public String goBackToWorldMap()
    {
        return "worldmap";
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
            return "destinations?faces-redirect=true";
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
        return "tripdetail?faces-redirect=true";
    }

    public String goToTripPayement()
    {
        tripController.setTrip(this.booking.getTrip());
        tripController.setNumberOfPassengers(this.booking.getNumberOfPassengers());
        return "trippayment?faces-redirect=true";
    }

    public String goBackToDestinations()
    {
        destinationsController.setCountry(this.country);
        return "destinations?faces-redirect=true";
    }

    public String goBackToTripDetail()
    {
        destinationsController.setCountry(this.country);
        return "tripdetail?faces-redirect=true";
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
}
