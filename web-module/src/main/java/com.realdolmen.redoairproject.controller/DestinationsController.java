package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.CountryRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class DestinationsController implements Serializable{

    @Inject
    private CountryRepository countryRepository;

    @Inject
    private TripRepository tripRepository;



    private List<Trip> tripsForDestination = new ArrayList<>();

    public List<Trip> retrieveAllTrips()
    {
        return tripRepository.findAll();
    }

    public String getDestinationFromCountry()
    {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String countryName = params.get("country");

        Country country = countryRepository.findCountryCodeByCountryCaseInsensitive(countryName);
        tripsForDestination = tripRepository.findTripsByCountry(country);

        if(country.getCountry() != null && !country.getCountry().equals(""))
            return country.getCountry();
        else
            return "";
    }

    public List<Trip> getTripsForDestination() {
        return tripsForDestination;
    }

    public void setTripsForDestination(List<Trip> tripsForDestination) {
        this.tripsForDestination = tripsForDestination;
    }
}
