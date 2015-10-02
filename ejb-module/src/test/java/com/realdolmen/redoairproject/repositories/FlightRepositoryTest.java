package com.realdolmen.redoairproject.repositories;

import com.realdolmen.redoairproject.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.redoairproject.repositories.FlightRepository;
import org.junit.*;

public class FlightRepositoryTest
{

    private Flight flight;
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
        Assert.fail("To be implemented");
    }
}
