package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TripRepositoryTest extends PersistenceTest{

    private static final Logger LOG = LoggerFactory.getLogger(TripRepositoryTest.class);

    private TripRepository tripRepository = new TripRepository();

    private CountryRepository countryRepository = new CountryRepository();

    private Country country;

    @Before
    public void before()
    {
        countryRepository.entityManager = entityManager();
        tripRepository.entityManager = entityManager();
    }

    @Test
    public void testFindTripsByCountry()
    {
        country = countryRepository.findCountryCodeByCountryCaseInsensitive("France");

        List<Trip> allTripsForCountry = tripRepository.findTripsByCountry(country);

        Assert.assertTrue(allTripsForCountry.size() > 0);

        for(Trip t : allTripsForCountry)
            LOG.debug(t.toString());
    }

    @Test
    public void testFindAllCountriesFromTrips()
    {
        List<Country> countries = tripRepository.findAllCountriesFromTrips();

        for (Country country1 : countries) {
            List<Trip> tripsByCountry = tripRepository.findTripsByCountry(country1);
            Assert.assertTrue(tripsByCountry.size() > 0);
        }

    }
}
