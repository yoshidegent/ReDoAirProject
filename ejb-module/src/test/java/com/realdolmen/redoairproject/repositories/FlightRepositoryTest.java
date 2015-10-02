package com.realdolmen.redoairproject.repositories;

import com.realdolmen.redoairproject.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.redoairproject.repositories.FlightRepository;
import org.junit.*;

import javax.inject.Inject;

public class FlightRepositoryTest
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
