package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Country;

/**
 * Created by YDEAX41 on 5/10/2015.
 */
public interface ICountryRepository {
    Country findCountryByCountryCodeCaseInsensitive(String countryCode);
    Country findCountryCodeByCountryCaseInsensitive(String country);
}
