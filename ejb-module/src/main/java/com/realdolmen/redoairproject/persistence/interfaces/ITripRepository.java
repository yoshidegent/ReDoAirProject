package com.realdolmen.redoairproject.persistence.interfaces;


import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ITripRepository extends IGenericRepository<Trip>
{
    List<Trip> findTripsByCountry(Country country);
    List<Country> findAllCountriesFromTrips();

    @SuppressWarnings("unchecked")
    List<String> findAllDestinationNames();

    List<Trip> findValidTrips(Country country, Date periodStart, Date periodEnd, int numberOfPassengers);
}
