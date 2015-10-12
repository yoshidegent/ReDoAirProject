package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.persistence.CountryRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class BookingConversationController
{
    @Inject
    private Conversation conversation;

    @Inject
    private WorldmapController worldmapController;

    @Inject
    private DestinationsController destinationsController;

    @Inject
    private CountryRepository countryRepository;

    public String startConversation()
    {
        conversation.begin();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap();
        String countryName = params.get("country");

        Country country = countryRepository.findCountryCodeByCountryCaseInsensitive(countryName);
        if("".equals(country.getCountryCode())) {
            conversation.end();
            return "worldmap";
        }
        else
        {
            destinationsController.setCountry(country);
            return destinationsController.getDestinationsFromCountry();
        }
    }
}
