package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Flight;

import javax.ejb.Remote;

@Remote
public interface IFlightRepository extends IGenericRepository<Flight>{
}
