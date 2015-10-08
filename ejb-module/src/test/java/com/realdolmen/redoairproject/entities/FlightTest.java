package com.realdolmen.redoairproject.entities;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class FlightTest {

    private static final Logger LOG = LoggerFactory.getLogger(FlightTest.class);

    private Flight flight;

    private final double GAMMA = 1e-15;
    private final double PRICE_PER_SEAT = 100.0;

    @Before
    public void before()
    {
        flight = new Flight(PRICE_PER_SEAT);
    }

    @Test
    public void testOverridePriceForPassenger()
    {
        Assert.assertEquals(flight.getPricePerSeat(), PRICE_PER_SEAT, GAMMA);
        double overriddenPrice = 200.0;
        flight.overridePriceForPassenger(overriddenPrice);
        //Check the price for the passenger has changed
        Assert.assertEquals(overriddenPrice, flight.getPricePerSeatForPassenger(), GAMMA);
        //Check if original price is not overridden
        Assert.assertEquals(flight.getPricePerSeat(), PRICE_PER_SEAT, GAMMA);
    }

    @Test
    public void testCalculateTotalProfitForFlight()
    {
        double pricePerSeat[] = new double[]{200, 100, -20, -56456};
        double overridenPrice[] = new double[]{200, 0, 80, 45564};
        double discount[] = new double[]{0.05, 5, 0, 45454};
        int seatsSold[] = new int[]{10, 5, -80, 4645645};
        int threshold[] = new int[]{30, 5, 0, 546};

        for (int i=0; i< pricePerSeat.length && i< overridenPrice.length && i< discount.length && i< seatsSold.length && i< threshold.length; i++)
        {
            flight = new Flight(pricePerSeat[i]);
            flight.setSeatsThresholdForDiscount(threshold[i]);
            flight.overridePriceForPassenger(overridenPrice[i]);
            flight.setAirline(new Airline(0l, "test", discount[i]));
            flight.setSeatCapacity(seatsSold[i]);

            if(threshold[i] > seatsSold[i])
                discount[i] = 0;

            double profit = (flight.getPricePerSeatForPassenger() - (flight.getPricePerSeat() * (1 - discount[i]))) * seatsSold[i];
            LOG.debug("Profit: " + profit);

            //Checkif the calculation is right
            Assert.assertEquals(profit, flight.calculateTotalProfitMargin(), GAMMA);
        }
    }


    @Test
    public void testPricesCantBeNegative()
    {
        final double price = 50;
        flight.setPricePerSeat(-price);
        Assert.assertEquals(price, flight.getPricePerSeat(), GAMMA);
    }


    @Test
    public void testPriceForPassengerCantBeNegative()
    {
        final double price = 50;
        flight.overridePriceForPassenger(-price);
        Assert.assertEquals(price, flight.getPricePerSeatForPassenger(), GAMMA);
    }


    @Test
    public void testCalculateArrivalTime()
    {
        flight.setDepartureTime(LocalTime.of(23, 00));
        flight.setFlightDurationInMinutes(120);
        Assert.assertEquals(LocalTime.of(01,00), flight.calculateArrivalTime());
    }
}




















