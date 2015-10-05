package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.IAirlineRepository;
import com.realdolmen.redoairproject.persistence.interfaces.IFlightRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class AirlineRepository extends GenericRepository<Airline> implements IAirlineRepository {

    public AirlineRepository() {
        super(Airline.class);
    }

}
