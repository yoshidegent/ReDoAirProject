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
public class TripControllerConv implements Serializable {
    private Trip trip = new Trip();

    @Inject
    private TripRepository tripRepository;


    public Trip retrieveTripById(long id)  {
        return tripRepository.findById(id);
    }


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
