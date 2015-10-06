package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import com.realdolmen.redoairproject.entities.Country;
import javax.persistence.TypedQuery;
import java.util.List;

public class TripRepository extends GenericRepository<Trip> implements ITripRepository {

    public TripRepository() {
        super(Trip.class);
    }

    @Override public List<Trip> findTripsByCountry(Country country) {
        TypedQuery<Trip> query = entityManager.createQuery("SELECT t FROM Trip t WHERE (t.endDestination.address.country) = :country", Trip.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Override public List<Country> findAllCountriesFromTrips() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT t.endDestination.address.country FROM Trip t", Country.class);
        return query.getResultList();
    }
}
