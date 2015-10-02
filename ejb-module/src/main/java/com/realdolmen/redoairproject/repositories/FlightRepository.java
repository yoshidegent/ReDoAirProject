package com.realdolmen.redoairproject.repositories;

import com.realdolmen.redoairproject.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.repositories.interfaces.IFlightRepository;

public class FlightRepository extends GenericRepository<Flight> implements IFlightRepository{
    public FlightRepository() {
        super(Flight.class);
    }
}
