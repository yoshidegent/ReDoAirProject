package com.realdolmen.redoairproject.entities;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by YDEAX41 on 10/10/2015.
 */
public class UserTest
{
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private User user;

    @Test
    public void testPasswordIsHashed()
    {
        String passwordToBeTested;
        String hashedPassword;

        passwordToBeTested = "password";
        user = new Passenger("testPassenger", passwordToBeTested);
        hashedPassword = new String (user.hashPassword(passwordToBeTested));
        //Check if the passwords can becompared after hashing
        Assert.assertEquals(new String(user.getHashedPassword()), hashedPassword);
        //Check if the hashed password is not equal to the original one
        Assert.assertNotEquals(passwordToBeTested, hashedPassword);
        LOG.debug("Hashed password: " + hashedPassword);

        passwordToBeTested = "";
        user = new Partner("testPassenger", passwordToBeTested);
        hashedPassword = new String (user.hashPassword(passwordToBeTested));
        Assert.assertEquals(new String(user.getHashedPassword()), hashedPassword);
        Assert.assertNotEquals(passwordToBeTested, hashedPassword);
        LOG.debug("Hashed password: " + hashedPassword);

        passwordToBeTested = "µùmùµùmdfs^$!!((&é)éà^^^^$$µùù54648431123é2@@@@";
        user = new ReDoEmployee("testPassenger", passwordToBeTested);
        hashedPassword = new String (user.hashPassword(passwordToBeTested));
        Assert.assertEquals(new String(user.getHashedPassword()), hashedPassword);
        Assert.assertNotEquals(passwordToBeTested, hashedPassword);
        LOG.debug("Hashed password: " + hashedPassword);
    }
}
