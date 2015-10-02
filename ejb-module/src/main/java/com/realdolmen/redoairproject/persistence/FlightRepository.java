package com.realdolmen.redoairproject.persistence;


import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.IFlightRepository;

public class FlightRepository extends GenericRepository<Flight> implements IFlightRepository {
    public FlightRepository() {
        super(Flight.class);
    }
}
