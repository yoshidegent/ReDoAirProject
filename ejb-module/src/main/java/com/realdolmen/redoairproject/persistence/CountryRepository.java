package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.interfaces.ICountryRepository;

public class CountryRepository extends GenericRepository<Country> implements ICountryRepository
{
    public CountryRepository() {
        super(Country.class);
    }
}
