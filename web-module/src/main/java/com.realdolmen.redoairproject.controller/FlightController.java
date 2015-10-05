package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FlightController {
    Flight flight = new Flight();


    @Inject
    FlightRepository repository;


    public List<Flight> retrieveAllFlights() {
        return repository.findAll();
    }

    public void removeFlight(Flight flightToBeRemoved) {
        repository.delete(flightToBeRemoved);
    }

    public void createOrUpdateFlight() {
        repository.createOrUpdate(flight);
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
