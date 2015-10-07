package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.AirlineRepository;
import com.realdolmen.redoairproject.persistence.AirportRepository;
import com.realdolmen.redoairproject.persistence.FlightRepository;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class FlightController {
    /**
     * Class fields
     */
    Flight flight = new Flight();
    long airlineId;
    long airportDestinationId;
    long airportOriginId;
    Date departureDate;


    /**
     * Inject repositories
     */
    @Inject
    FlightRepository flightRepository;

    @Inject
    AirlineRepository airlineRepository;

    @Inject
    AirportRepository airportRepository;


    /**
     * Methods
     */
    public List<Flight> retrieveAllFlights() {
        return flightRepository.findAll();
    }


    public List<Airline> retrieveAllAirlines() {
        return airlineRepository.findAll();
    }

    public List<Airport> retrieveAllAirports() {
        return airportRepository.findAll();
    }

    public String createOrUpdateFlight() {
        flight.setAirline(airlineRepository.findById(airlineId));
        flight.setOrigin(airportRepository.findById(airportOriginId));
        flight.setDestination(airportRepository.findById(airportDestinationId));
        flight.setTestDate(departureDate);
        flightRepository.createOrUpdate(flight);
        return "flightsall";
    }


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    /**
     * Getters & setters
     */
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
