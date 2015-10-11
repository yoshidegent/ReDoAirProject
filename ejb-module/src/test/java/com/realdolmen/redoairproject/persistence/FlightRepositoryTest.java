package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Flight;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class
        FlightRepositoryTest extends PersistenceTest
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

    @Ignore
    @Test
    public void departureDatesAndTimesCanBeUpdated() {
        Flight flight500 = flightRepository.findById(500l);
        flight500.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight500.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight500.getDepartureDate(),
            flightRepository.findById(500l).getDepartureDate());
        Assert.assertEquals(flight500.getDepartureTime(),
            flightRepository.findById(500l).getDepartureTime());

        Flight flight501 = flightRepository.findById(501l);
        flight501.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight501.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight501.getDepartureDate(),
            flightRepository.findById(501l).getDepartureDate());
        Assert.assertEquals(flight501.getDepartureTime(),
            flightRepository.findById(501l).getDepartureTime());

        Flight flight502 = flightRepository.findById(502l);
        flight502.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight502.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight502.getDepartureDate(),
            flightRepository.findById(502l).getDepartureDate());
        Assert.assertEquals(flight502.getDepartureTime(),
            flightRepository.findById(502l).getDepartureTime());

        Flight flight503 = flightRepository.findById(503l);
        flight503.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight503.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight503.getDepartureDate(),
            flightRepository.findById(503l).getDepartureDate());
        Assert.assertEquals(flight503.getDepartureTime(),
            flightRepository.findById(503l).getDepartureTime());

        Flight flight504 = flightRepository.findById(504l);
        flight504.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight504.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight504.getDepartureDate(),
            flightRepository.findById(504l).getDepartureDate());
        Assert.assertEquals(flight504.getDepartureTime(),
            flightRepository.findById(504l).getDepartureTime());


        Flight flight505= flightRepository.findById(505l);
        flight505.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight505.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight505.getDepartureDate(),
            flightRepository.findById(505l).getDepartureDate());
        Assert.assertEquals(flight505.getDepartureTime(),
            flightRepository.findById(505l).getDepartureTime());


        Flight flight506 = flightRepository.findById(506l);
        flight506.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight506.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight506.getDepartureDate(),
            flightRepository.findById(506l).getDepartureDate());
        Assert.assertEquals(flight506.getDepartureTime(),
            flightRepository.findById(506l).getDepartureTime());


        Flight flight507 = flightRepository.findById(507l);
        flight507.setDepartureDate(LocalDate.of(2015, 10, 24));
        flight507.setDepartureTime(LocalTime.of(9, 15));
        Assert.assertEquals(flight507.getDepartureDate(),
            flightRepository.findById(507l).getDepartureDate());
        Assert.assertEquals(flight507.getDepartureTime(),
            flightRepository.findById(507l).getDepartureTime());

        Flight flight508 = flightRepository.findById(508l);
        flight508.setDepartureDate(LocalDate.of(2015, 10, 25));
        flight508.setDepartureTime(LocalTime.of(22, 50));
        Assert.assertEquals(flight508.getDepartureDate(),
            flightRepository.findById(508l).getDepartureDate());
        Assert.assertEquals(flight508.getDepartureTime(),
            flightRepository.findById(508l).getDepartureTime());


        Flight flight509 = flightRepository.findById(509l);
        flight509.setDepartureDate(LocalDate.of(2015, 12, 23));
        flight509.setDepartureTime(LocalTime.of(10, 30));
        Assert.assertEquals(flight509.getDepartureDate(),
            flightRepository.findById(509l).getDepartureDate());
        Assert.assertEquals(flight509.getDepartureTime(),
            flightRepository.findById(509l).getDepartureTime());

        Flight flight510 = flightRepository.findById(510l);
        flight510.setDepartureDate(LocalDate.of(2015, 12, 24));
        flight510.setDepartureTime(LocalTime.of(21, 50));
        Assert.assertEquals(flight510.getDepartureDate(),
            flightRepository.findById(510l).getDepartureDate());
        Assert.assertEquals(flight510.getDepartureTime(),
            flightRepository.findById(510l).getDepartureTime());
    }
}
