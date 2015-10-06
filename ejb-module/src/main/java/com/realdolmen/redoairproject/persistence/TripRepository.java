package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

/**
 * Created by EWTAX45 on 6/10/2015.
 */
public class TripRepository extends GenericRepository<Trip> implements ITripRepository {

    public TripRepository() {
        super(Trip.class);
    }

}
