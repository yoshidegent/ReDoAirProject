package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Flight;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class FlightRepositoryTest extends PersistenceTest
{
    private static final Logger LOG = LoggerFactory.getLogger(FlightRepositoryTest.class);

    private Flight flight;

    @Inject
    private FlightRepository flightRepository;

    @Before
    public void beforeClass()
    {
        flight = new Flight();
        flightRepository = new FlightRepository();
        flightRepository.entityManager = entityManager();
    }

    @Test
    public void testFlightPersists()
    {
        //Persist an empty flight object
        flight = flightRepository.createOrUpdate(flight);
        Assert.assertNotNull(flight.getId());

        //TODO: implement other test cases
    }

    @Test
    public void testFindAllFlights()
    {
        //Save a flight
        flightRepository.createOrUpdate(flight);


        List<Flight> flightList = flightRepository.findAll();

        for(Flight f : flightList)
        {
            LOG.debug(f.toString());
        }

        Assert.assertTrue(flightList.size() > 0);
    }

    @Test
    public void testFlightCanBeUpdated()
    {
        //Save a flight
        flight = flightRepository.createOrUpdate(flight);

        //Data before update
        Long idBeforeUpdate = flight.getId();
        int seatsAvailableBeforeUpdate = flight.getSeatsAvailable();

        //Update the same flight
        flight.setSeatsAvailable(100);
        flight = flightRepository.createOrUpdate(flight);

        //Data after update
        Long idAfterUpdate = flight.getId();
        int seatsAvailableAfterUpdate = flight.getSeatsAvailable();

        //Check if id stayed the same, but updated data changed
        Assert.assertNotEquals(seatsAvailableAfterUpdate, seatsAvailableBeforeUpdate);
        Assert.assertEquals(idAfterUpdate, idBeforeUpdate);
    }

    @Test
    public void testFindFlightById()
    {
        //Save a flight
        flight = flightRepository.createOrUpdate(flight);

        Long id = flight.getId();

        Flight flightFoundById = flightRepository.findById(id);

        Assert.assertEquals(flight, flightFoundById);
    }

    @Test
    public void testDeleteFlight()
    {
        //Save a flight
        flight = flightRepository.createOrUpdate(flight);

        Long id = flight.getId();

        flightRepository.delete(flight);

        Assert.assertNull(flightRepository.findById(id));
    }
}
