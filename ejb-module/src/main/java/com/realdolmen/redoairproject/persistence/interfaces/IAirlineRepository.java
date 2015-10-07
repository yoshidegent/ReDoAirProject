package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Airline;

import javax.ejb.Remote;

@Remote
public interface IAirlineRepository extends IGenericRepository<Airline> {
}
