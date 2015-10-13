package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Airline;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IAirlineRepository extends IGenericRepository<Airline> {
    public Airline findAirlineByName(String name);
}
