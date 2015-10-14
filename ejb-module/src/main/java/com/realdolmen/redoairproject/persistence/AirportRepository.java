package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.persistence.interfaces.IAirportRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * Created by EWTAX45 on 5/10/2015.
 */
@Stateless
@LocalBean
public class AirportRepository extends GenericRepository<Airport> implements IAirportRepository, Serializable {

    public AirportRepository() {
        super(Airport.class);
    }

}
