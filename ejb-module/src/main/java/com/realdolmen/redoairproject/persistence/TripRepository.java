package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import com.realdolmen.redoairproject.entities.Country;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@LocalBean
@Stateless
public class TripRepository extends GenericRepository<Trip> implements ITripRepository, Serializable {

    public TripRepository() {
        super(Trip.class);
    }

    @Override public List<Trip> findTripsByCountry(Country country) {
        TypedQuery<Trip> query = entityManager.createQuery("SELECT t FROM Trip t WHERE t.endDestination.address.country.countryCode = :countryCode", Trip.class);
        query.setParameter("countryCode", country.getCountryCode());
        return query.getResultList();
    }


    @Override public List<Country> findAllCountriesFromTrips() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT t.endDestination.address.country FROM Trip t", Country.class);
        return query.getResultList();
    }
}
