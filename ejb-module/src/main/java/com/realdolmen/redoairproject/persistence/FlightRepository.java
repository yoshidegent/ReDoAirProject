package com.realdolmen.redoairproject.persistence;


import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.IFlightRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

public class FlightRepository extends GenericRepository<Flight> implements IFlightRepository {
    public FlightRepository() {
        super(Flight.class);
    }
}
