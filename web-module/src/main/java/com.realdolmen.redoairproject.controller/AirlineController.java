package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.AirlineRepository;
import com.realdolmen.redoairproject.persistence.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
@RequestScoped
public class AirlineController {

    Airline airline = new Airline();

    @Inject
    AirlineRepository repository;

//    public List<Airline> retrieveAllAirlines()   {
//        return repository.findAll();
//    }

    public Airline retrieveAirlineForTesting()  {
        return repository.findById(2l);
    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
