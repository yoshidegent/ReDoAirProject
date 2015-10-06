package com.realdolmen.redoairproject.entities;

import org.junit.*;

/**
 * Created by YDEAX41 on 6/10/2015.
 */
public class FlighTest {

    private Flight flight;

    @Before
    public void before()
    {
        flight = new Flight();
        //Set needed data
    }

    @Test
    public void testOverridePriceForPassenger()
    {
        Assert.fail("To be implemented");
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
