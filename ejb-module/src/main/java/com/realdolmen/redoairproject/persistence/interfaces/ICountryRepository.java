package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Country;

import javax.ejb.Remote;

@Remote
public interface ICountryRepository {
    Country findCountryByCountryCodeCaseInsensitive(String countryCode);
    Country findCountryCodeByCountryCaseInsensitive(String country);
}
