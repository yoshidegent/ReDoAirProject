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
public class FlightController {
    Flight flight = new Flight();
    Airline airline = new Airline();

    @Inject
    FlightRepository repository;

    @Inject
    AirlineRepository airlineRepository;


    public List<Flight> retrieveAllFlights() {
        return repository.findAll();
    }

    public void removeFlight(Flight flightToBeRemoved) {
        repository.delete(flightToBeRemoved);
    }

    public String createOrUpdateFlight() {
        flight.setAirline(airline);
        repository.createOrUpdate(flight);
        return "flightsall";
    }


    public List<Airline> retrieveAllAirlines() {
        List<Airline> all = airlineRepository.findAll();
        return all;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
