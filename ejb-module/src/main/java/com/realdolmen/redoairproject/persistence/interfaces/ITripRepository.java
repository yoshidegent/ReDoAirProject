package com.realdolmen.redoairproject.persistence.interfaces;


import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;

import java.util.List;

/**
 * Created by YDEAX41 on 6/10/2015.
 */
public interface ITripRepository extends IGenericRepository<Trip>
{
    List<Trip> findTripsByCountry(Country country);
    List<Country> findAllCountriesFromTrips();
}
