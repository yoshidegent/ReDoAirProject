package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.ICountryRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CountryRepository extends GenericRepository<Country> implements ICountryRepository
{
    public CountryRepository() {
        super(Country.class);
    }

    @Override
    public Country findCountryByCountryCodeCaseInsensitive(String countryCode) {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c WHERE lower(c.countryCode) = lower(:countryCode)", Country.class);
        query.setParameter("countryCode", countryCode);
        try {
            return query.getSingleResult();
        }
        catch (NoResultException nre)
        {
            return new Country();
        }

    }

    @Override
    public Country findCountryCodeByCountryCaseInsensitive(String country) {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c WHERE lower(c.country) = lower(:country)", Country.class);
        query.setParameter("country", country);
        try {
            return query.getSingleResult();
        }
        catch (NoResultException nre)
        {
            return new Country();
        }
    }
}
