package com.realdolmen.redoairproject.persistence.repositories;

import com.realdolmen.redoairproject.entities.Flight;
import org.junit.*;

import javax.inject.Inject;

public class FlightRepositoryTest extends PersistenceTest
{

    private Flight flight;

    @Inject
    private FlightRepository flightRepository;

    @Before
    public void before()
    {
        flight = new Flight();
        flightRepository = new FlightRepository();
    }

    @Test
    public void testFlightPersists()
    {
        flight = flightRepository.createOrUpdate(flight);
        Assert.assertNotNull(flight.getId());
    }
}
