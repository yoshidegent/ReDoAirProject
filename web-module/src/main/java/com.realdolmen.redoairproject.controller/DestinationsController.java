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
import java.util.*;

@Named
@RequestScoped
public class DestinationsController implements Serializable{

    private Date from = new Date();
    private Date to = null;

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

    public Date getDateToday() {
        return new Date();
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        if(this.to == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 1);
            return calendar.getTime();
        }
        else
            return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
