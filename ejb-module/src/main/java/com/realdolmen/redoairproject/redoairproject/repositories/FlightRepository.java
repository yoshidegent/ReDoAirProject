package com.realdolmen.redoairproject.redoairproject.repositories;

import com.realdolmen.redoairproject.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.redoairproject.repositories.interfaces.IFlightRepository;

import javax.ejb.Stateless;

public class FlightRepository extends GenericRepository<Flight> implements IFlightRepository{
    public FlightRepository() {
        super(Flight.class);
    }
}
