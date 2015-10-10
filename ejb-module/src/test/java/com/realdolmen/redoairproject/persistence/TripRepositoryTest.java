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

    @Before
    public void before()
    {
        LOG.debug("*****************************************************************************************************************************************");
        countryRepository.entityManager = entityManager();
        tripRepository.entityManager = entityManager();
    }

    @Test
    public void testFindAllCountriesFromTrips()
    {
        List<Country> countries = tripRepository.findAllCountriesFromTrips();

        Assert.assertTrue(countries.size() > 0);

        for (Country country1 : countries) {
            List<Trip> tripsByCountry = tripRepository.findTripsByCountry(country1);
            Assert.assertTrue(tripsByCountry.size() > 0);
        }

        entityManager().clear();
    }

    @Test
    public void testFindTripsByCountry()
    {
        Country country = countryRepository.findCountryCodeByCountryCaseInsensitive("Belgium");

        List<Trip> allTripsForCountry = tripRepository.findTripsByCountry(country);

        Assert.assertTrue(allTripsForCountry.size() > 0);

        for(Trip t : allTripsForCountry)
            LOG.debug(t.toString());

        entityManager().clear();
    }
}
