package com.realdolmen.redoairproject.entities;

import org.junit.*;

/**
 * Created by YDEAX41 on 6/10/2015.
 */
public class FlighTest {

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
        Assert.fail("To be implemented");
    }


    @Test
    public void testPricesCantBeNegative()
    {
        Assert.fail("To be implemented");
    }
}
