package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Partner;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.ReDoEmployee;
import com.realdolmen.redoairproject.entities.User;
import com.realdolmen.redoairproject.persistence.interfaces.IUserRepository;

import javax.persistence.TypedQuery;

public class UserRepository extends GenericRepository<User> implements IUserRepository
{
    public User checkValidUser(User user)
    {
        //Check if user is a passenger
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM Passenger p WHERE p.username = :username AND p.hashedPassword = :password", User.class);

        if(query.getResultList().size() != 0)
            return (Passenger) query.getSingleResult();

        //check if user is a redoemployee
        query = entityManager.createQuery("SELECT e FROM ReDoEmployee e WHERE e.username = :username AND e.hashedPassword = :password", User.class);


        if(query.getResultList().size() != 0)
            return (ReDoEmployee) query.getSingleResult();

        //check if user is a partner
        query = entityManager.createQuery("SELECT p FROM Partner p WHERE p.username = :username AND p.hashedPassword = :password", User.class);

        if(query.getResultList().size() != 0)
            return (Partner) query.getSingleResult();

        //No user found
        return null;
    }
}
