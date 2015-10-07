package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Airport;

import javax.ejb.Remote;

@Remote
public interface IAirportRepository extends IGenericRepository<Airport> {
}
