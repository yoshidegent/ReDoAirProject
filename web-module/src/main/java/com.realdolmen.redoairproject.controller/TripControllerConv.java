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

    @Inject
    private TripRepository tripRepository;

    private Trip trip = new Trip();

    public Trip retrieveTripById(long id)  {
        return tripRepository.findById(id);
    }

}
