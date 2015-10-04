package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Flight;
import org.junit.*;

import javax.inject.Inject;

public class FlightRepositoryTest extends PersistenceTest
{

    private Flight flight;

    private FlightRepository flightRepository;

    @Before
    public void before()
    {
        flight = new Flight();
        flightRepository = new FlightRepository();
        flightRepository.entityManager = entityManager();
    }

    @Test
    public void testFlightPersists()
    {
        flight = flightRepository.createOrUpdate(flight);
        Assert.assertNotNull(flight.getId());
    }
}
