package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Partner;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.ReDoEmployee;
import com.realdolmen.redoairproject.entities.User;
import org.junit.*;

public class UserRepositoryTest extends PersistenceTest
{
    private UserRepository userRepository;

    private Passenger passenger;
    private Partner partner;
    private ReDoEmployee reDoEmployee;

    @Before
    public void before()
    {
        userRepository = new UserRepository();
        userRepository.entityManager = entityManager();

        passenger = new Passenger("testPassenger", "password");
        partner = new Partner("testPartner", "password");
        reDoEmployee = new ReDoEmployee("testReDoEmployee", "password");
    }

    @Test
    public void testUserPersists()
    {
        passenger = (Passenger) userRepository.createOrUpdate(passenger);
        Assert.assertNotNull(passenger.getId());

        partner = (Partner) userRepository.createOrUpdate(partner);
        Assert.assertNotNull(partner.getId());

        reDoEmployee = (ReDoEmployee) userRepository.createOrUpdate(reDoEmployee);
        Assert.assertNotNull(reDoEmployee.getId());
    }

    @Test
    public void testGetUserByUsername()
    {
        passenger = (Passenger) userRepository.createOrUpdate(passenger);
        partner = (Partner) userRepository.createOrUpdate(partner);
        reDoEmployee = (ReDoEmployee) userRepository.createOrUpdate(reDoEmployee);

        User user = new Passenger("mjklqsmdj", "jqdmkldmklqsm");

        Assert.assertTrue(
            userRepository.getUserByUsername(passenger.getUsername()) instanceof Passenger);
        Assert.assertTrue(
            userRepository.getUserByUsername(partner.getUsername()) instanceof Partner);
        Assert.assertTrue(
            userRepository.getUserByUsername(reDoEmployee.getUsername()) instanceof ReDoEmployee);
        Assert.assertFalse(
            userRepository.getUserByUsername(user.getUsername()) instanceof Passenger);


    }
}
