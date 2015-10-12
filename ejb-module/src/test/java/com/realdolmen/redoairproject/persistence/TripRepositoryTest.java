package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.converter.LocalDatePersistenceConverter;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.entities.Trip;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class TripRepositoryTest extends PersistenceTest{

    private static final Logger LOG = LoggerFactory.getLogger(TripRepositoryTest.class);

    private TripRepository tripRepository = new TripRepository();

    private CountryRepository countryRepository = new CountryRepository();

    @Before
    public void before()
    {
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
            for(Trip t : tripsByCountry)
            {
                Assert.assertNotNull(t.getFlightList());
                Assert.assertTrue(t.getFlightList().size() > 0);
            }
        }
    }

    @Test
    public void testFindTripsByCountry()
    {
        Country country = countryRepository.findCountryCodeByCountryCaseInsensitive("Belgium");

        List<Trip> allTripsForCountry = tripRepository.findTripsByCountry(country);

        Assert.assertTrue(allTripsForCountry.size() > 0);

        for(Trip t : allTripsForCountry)
            LOG.debug(t.toString());
    }

    @Test
    public void testBeginAndEndDatesAreNotNull()
    {
        List<Trip> allTrips = tripRepository.findAll();

        for (Trip trip : allTrips) {
            if (trip.getFlightList().size() > 0) {
                Assert.assertNotNull(trip.getBeginDate());
                Assert.assertNotNull(trip.getEndDate());
            }
        }
    }


    @Test
    public void testFindValidTrips()
    {
        Country country = new Country("GE", "Germany");
        Date startPeriod = new GregorianCalendar(1900,Calendar.JANUARY,31).getTime();
        Date endPeriod = new GregorianCalendar(2100,Calendar.JANUARY,31).getTime();
        int numberOfPassengers = 5;

        List<Trip> allTrips = tripRepository.findValidTrips(country, startPeriod, endPeriod, numberOfPassengers);

        for (Trip t : allTrips)
        {
            Assert.assertNotNull(t.getFlightList());
            Assert.assertTrue(t.getFlightList().size() > 0);
            Assert.assertTrue(t.getBeginDate().isAfter(new LocalDatePersistenceConverter().convertToEntityAttribute(new java.sql.Date(startPeriod.getTime()))));
            Assert.assertTrue(t.getEndDate().isBefore(new LocalDatePersistenceConverter().convertToEntityAttribute(new java.sql.Date(endPeriod.getTime()))));
        }
    }
}
