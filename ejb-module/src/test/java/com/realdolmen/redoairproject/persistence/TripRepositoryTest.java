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
        LOG.debug("*****************************************************************************************");
        tripRepository.entityManager = entityManager();
    }


    @After
    public void after() {
        tripRepository.entityManager.clear();
    }


    @Test
    public void testFindTripsByCountry()
    {
        country = new Country("AU", "Australia");

        List<Trip> allTripsForCountry = tripRepository.findTripsByCountry(country);

        Assert.assertTrue(allTripsForCountry.size() > 0);

        for(Trip t : allTripsForCountry)
            LOG.debug(t.toString());

    }

    @Test
    public void testFindAllCountriesFromTrips()
    {
        List<Country> countries = tripRepository.findAllCountriesFromTrips();

//        for (Country c : countries) {
//            List<Trip> tripsByCountry = tripRepository.findTripsByCountry(c);
//            Assert.assertTrue(tripsByCountry.size() > 0);
//        }

        LOG.debug("i'm the endpoint of test findAllCountriesFromTrips");

    }
}






























