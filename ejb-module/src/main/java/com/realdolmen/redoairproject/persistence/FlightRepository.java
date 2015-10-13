package com.realdolmen.redoairproject.persistence;


import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.IFlightRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class FlightRepository extends GenericRepository<Flight> implements IFlightRepository, Serializable {
    public FlightRepository() {
        super(Flight.class);
    }

    @Override public List<Flight> findFlightsByAirline(Airline airline) {
        TypedQuery<Flight> query = entityManager.createQuery("SELECT f FROM Flight f WHERE f.airline.id = :airlineId", Flight.class);
        query.setParameter("airlineId", airline.getId());
        return query.getResultList();
    }
}
