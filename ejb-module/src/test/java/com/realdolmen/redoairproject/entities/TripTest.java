package com.realdolmen.redoairproject.entities;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class TripTest {
    private static final Logger LOG = LoggerFactory.getLogger(FlightTest.class);


    private Trip trip;

    private final double GAMMA = 1e-15;
    private final double FLIGHT_PRICE_1 = 500;
    private final double FLIGHT_PRICE_2 = 200;
    private final double FLIGHT_PRICE_3 = 100;
    private final double HOTEL_PRICE_PER_NIGHT = 80;
    private final int NUMBER_OF_NIGHTS = 8;
    private final int NUMBER_OF_PASSENGERS = 4;
    private final LocalDate dateFlight1 = LocalDate.of(2015, 10, 15);
    private final LocalDate dateFlight2 = LocalDate.of(2015, 10, 18);
    private final LocalDate dateFlight3 = LocalDate.of(2015, 11, 10);
    private final LocalTime timeflights = LocalTime.of(23,00);

    @Before
    public void before()
    {
        trip = new Trip();
        List<Flight> flightList = new ArrayList<>();

        Flight flight1 = new Flight();
        flight1.overridePriceForPassenger(FLIGHT_PRICE_1);
        flight1.setDepartureDate(dateFlight1);
        flight1.setDepartureTime(timeflights);
        flight1.setFlightDurationInMinutes(500);

        Flight flight2 = new Flight();
        flight2.overridePriceForPassenger(FLIGHT_PRICE_2);
        flight2.setDepartureDate(dateFlight2);
        flight2.setDepartureTime(timeflights);
        flight2.setFlightDurationInMinutes(500);

        Flight flight3 = new Flight();
        flight3.overridePriceForPassenger(FLIGHT_PRICE_3);
        flight3.setDepartureDate(dateFlight3);
        flight3.setDepartureTime(timeflights);
        flight3.setFlightDurationInMinutes(500);


        flightList.add(flight1);
        flightList.add(flight3);
        flightList.add(flight2);
        trip.setFlightList(flightList);
        trip.setPriceHotelPerNightPerPerson(HOTEL_PRICE_PER_NIGHT);
        trip.setNumberOfNights(NUMBER_OF_NIGHTS);
    }


    @Test
    public void testCalculateTotalPrice()   {
        double totalPricePerPerson = FLIGHT_PRICE_1 + FLIGHT_PRICE_2 + FLIGHT_PRICE_3 + HOTEL_PRICE_PER_NIGHT * NUMBER_OF_NIGHTS;
        Assert.assertEquals(totalPricePerPerson*NUMBER_OF_PASSENGERS, trip.calculateTotalPrice(NUMBER_OF_PASSENGERS), GAMMA);
    }

    @Test
    public void testCalculateTotalPriceForAllFlights()  {
        double totalPricePerPerson = FLIGHT_PRICE_1 + FLIGHT_PRICE_2 + FLIGHT_PRICE_3;
        Assert.assertEquals(totalPricePerPerson, trip.calculatePriceForAllFlights(), GAMMA);
    }

    @Test
    public void testCalculateBeginDate()    {
        Assert.assertEquals(dateFlight1, trip.calculateBeginDate());
    }

    @Test
    public void testCalculateEndDate()  {
        LocalDate realEndDate = dateFlight3.plusDays(1);
        Assert.assertEquals(realEndDate, trip.calculateEndDate());
    }

    @Test
    public void testCalculateDurationOfTrip()   {
        long duration = dateFlight3.toEpochDay() - dateFlight1.toEpochDay();
        Assert.assertEquals(duration +1 , trip.calculateDurationOfTrip());
    }
}
