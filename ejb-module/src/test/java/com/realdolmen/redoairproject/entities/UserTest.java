package com.realdolmen.redoairproject.entities;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest
{
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Test
    public void testPasswordIsHashedAndValid()
    {
        String passwordToBeTested;

        passwordToBeTested = "password";
        User user = new Passenger("testPassenger", passwordToBeTested);
        //Check if the passwords can becompared after hashing
        Assert.assertTrue(user.checkPasswordIsValid(passwordToBeTested));
        //Check if the hashed password is not equal to the original one
        Assert.assertNotEquals(passwordToBeTested, user.getHashedPasswordAsString());
        LOG.debug("Hashed password: " + user.getHashedPasswordAsString());

        passwordToBeTested = "";
        user = new Partner("testPassenger", passwordToBeTested);
        Assert.assertTrue(user.checkPasswordIsValid(passwordToBeTested));
        Assert.assertNotEquals(passwordToBeTested, user.getHashedPasswordAsString());
        LOG.debug("Hashed password: " + user.getHashedPasswordAsString());

        passwordToBeTested = "µùmùµùmdfs^$!!((&é)éà^^^^$$µùù54648431123##^{^@@@||@é2@@@@";
        user = new ReDoEmployee("testPassenger", passwordToBeTested);
        Assert.assertTrue(user.checkPasswordIsValid(passwordToBeTested));
        Assert.assertNotEquals(passwordToBeTested, user.getHashedPasswordAsString());
        LOG.debug("Hashed password: " + user.getHashedPasswordAsString());
    }
}
