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

import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class TripRepository extends GenericRepository<Trip> implements ITripRepository {

    @Override public List<Trip> findTripsByCountry(Country country) {
        TypedQuery<Trip> query = entityManager.createQuery("SELECT t FROM Trip t WHERE (t.airport.address.country) = :country", Trip.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Override public List<Country> findAllCountriesFromTrips() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT t.address.country FROM Trip t", Country.class);
        return query.getResultList();
    }
}
