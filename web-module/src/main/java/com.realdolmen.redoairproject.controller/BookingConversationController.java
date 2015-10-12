package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.CountryRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@RequestScoped
public class BookingConversationController
{
    @Inject
    private Conversation conversation;

    @Inject
    private TripController tripController;

    @Inject
    private DestinationsController destinationsController;

    @Inject
    private CountryRepository countryRepository;

    private Country country;
    private Trip trip;

    public String goBackToWorldMap()
    {
        return "worldmap";
    }

    public void startConversation() {
        conversation.begin();
    }

    public String goToDestinations()
    {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String countryName = params.get("country");

        country = countryRepository.findCountryCodeByCountryCaseInsensitive(countryName);
        if("".equals(country.getCountryCode())) {
            return goBackToWorldMap();
        }
        else {
            destinationsController.setCountry(country);
            destinationsController.getDestinationsFromCountry();
            return "destinations";
        }
    }

    public String goToTripDetail()
    {
        int numberOfPassengers = destinationsController.getNumberOfPassengers();
        tripController.setNumberOfPassengers(numberOfPassengers);
        return "tripdetail";
    }

    public String goToTripPayement()
    {
        return "trippayment";
    }

    public String goBackToDestinations()
    {
        return "destinations?faces-redirect=true";
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
