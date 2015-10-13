package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Flight;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IFlightRepository extends IGenericRepository<Flight>{

    List<Flight> findFlightsByAirline(Airline airline);
}
