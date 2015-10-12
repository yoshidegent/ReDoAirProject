package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.converter.LocalDatePersistenceConverter;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.CountryRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Named
@ConversationScoped
public class DestinationsController implements Serializable{

    private Date from = new Date();
    private Date to = null;

    private int numberOfPassengers = 1;
    private Country country;



    @Inject
    private TripRepository tripRepository;

    private List<Trip> tripsForDestination = new ArrayList<>();

    public List<Trip> retrieveAllTrips()
    {
        return tripRepository.findAll();
    }

    public void refreshTripList()
    {
        tripsForDestination = tripRepository.findValidTrips(country, from, to, numberOfPassengers);
    }

    public void getDestinationsFromCountry()
    {
        to = getTo();

        refreshTripList();
    }

    public void dateChange() {
        refreshTripList();
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
