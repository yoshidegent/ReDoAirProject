package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.*;
import com.realdolmen.redoairproject.persistence.AirlineRepository;
import com.realdolmen.redoairproject.persistence.AirportRepository;
import com.realdolmen.redoairproject.persistence.FlightRepository;
import com.realdolmen.redoairproject.persistence.UserRepository;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
    String departureTime;
    @ManagedProperty("#{param.id}")
    private Long flightId;
    @ManagedProperty("#{param.empId}")
    private String username;


    /**
     * Inject repositories
     */
    @Inject
    FlightRepository flightRepository;

    @Inject
    AirlineRepository airlineRepository;

    @Inject
    AirportRepository airportRepository;

    @Inject
    UserRepository userRepository;

    private User user;

    /**
     * Methods
     */

    public void onEdit()    {
        flight = flightRepository.findById(flightId);
        airlineId = flight.getAirline().getId();
        airportDestinationId = flight.getDestination().getId();
        airportOriginId = flight.getOrigin().getId();
        departureDate = Date.from(flight.getDepartureDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        departureTime = flight.getDepartureTime().toString();
    }

    public boolean checkUserIsPartner()
    {
        return user instanceof Partner;
    }

    public boolean checkUserIsReDoEmployee()
    {
        return user instanceof ReDoEmployee;
    }

    public User retrieveUserById(String username)
    {
        return userRepository.getUserByUsername(username);
    }

    public void setUser()
    {
        this.user = retrieveUserById(this.username);
    }

    public List<Flight> retrieveAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> retrieveFlightsByAirline(Airline airline) {
        return flightRepository.findFlightsByAirline(airline);
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

        LocalDate date = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String[] split = departureTime.split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        flight.setDepartureTime(time);
        flight.setDepartureDate(date);
        flight.setId(flightId);
        if (flight.getId() == null) {
            flightRepository.createOrUpdate(flight);
            return "flightsall";
        }   else    {
            flightRepository.createOrUpdate(flight);
            return "flightdetail?faces-redirect=true&id=" + flight.getId();
        }

    }

    public Flight getFlightById(int id)   {
        return flightRepository.findById((long) id);
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Flight> getFlightList()
    {
        if(this.user instanceof Partner)
        {
            return this.retrieveFlightsByAirline(((Partner)user).getAirline());
        }
        else
        {
            if(this.user instanceof ReDoEmployee)
            {
                return this.retrieveAllFlights();
            }
        }

        return null;
    }
}
