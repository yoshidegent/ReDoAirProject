package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.IAirlineRepository;
import com.realdolmen.redoairproject.persistence.interfaces.IFlightRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@LocalBean
public class AirlineRepository extends GenericRepository<Airline> implements IAirlineRepository {

    public AirlineRepository() {
        super(Airline.class);
    }

    @Override public Airline findAirlineByName(String name) {
        TypedQuery<Airline> query = entityManager.createQuery("SELECT a FROM Airline a WHERE a.name = :name", Airline.class);
        query.setParameter("name", name);
        if(query.getResultList().size() > 0)
            return query.getSingleResult();
        else
            return null;
    }
}
