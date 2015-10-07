package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.AirlineRepository;
import com.realdolmen.redoairproject.persistence.AirportRepository;
import com.realdolmen.redoairproject.persistence.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FlightController {
    Flight flight = new Flight();
    long airlineId;
    long airportDestinationId;
    long airportOriginId;

    @Inject
    FlightRepository flightRepository;

    @Inject
    AirlineRepository airlineRepository;

    @Inject
    AirportRepository airportRepository;


    public List<Flight> retrieveAllFlights() {
        return flightRepository.findAll();
    }


    public List<Airline> retrieveAllAirlines() {
        List<Airline> all = airlineRepository.findAll();
        return all;
    }


    public List<Airport> retrieveAllAirports()  {
        return airportRepository.findAll();
    }

    public String createOrUpdateFlight() {
        flight.setAirline(airlineRepository.findById(airlineId));
        flight.setOrigin(airportRepository.findById(airportOriginId));
        flight.setDestination(airportRepository.findById(airportDestinationId));
        flightRepository.createOrUpdate(flight);
        return "flightsall";
    }



    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(long airlineId) {
        this.airlineId = airlineId;
    }

    public long getAirportDestinationId() {
        return airportDestinationId;
    }

    public void setAirportDestinationId(long airportDestinationId) {
        this.airportDestinationId = airportDestinationId;
    }

    public long getAirportOriginId() {
        return airportOriginId;
    }

    public void setAirportOriginId(long airportOriginId) {
        this.airportOriginId = airportOriginId;
    }
}
