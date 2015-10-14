package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Partner;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.ReDoEmployee;
import com.realdolmen.redoairproject.entities.User;
import com.realdolmen.redoairproject.persistence.interfaces.IUserRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@LocalBean
@Stateless
public class UserRepository extends GenericRepository<User> implements IUserRepository, Serializable
{
    public User getUserByUsername(String username)
    {
        //Check if user is a passenger
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM Passenger p WHERE p.username = :username", User.class);
        query.setParameter("username", username);

        if(query.getResultList().size() != 0)
            return (Passenger) query.getSingleResult();

        //check if user is a redoemployee
        query = entityManager.createQuery("SELECT e FROM ReDoEmployee e WHERE e.username = :username", User.class);
        query.setParameter("username", username);

        if(query.getResultList().size() != 0)
            return (ReDoEmployee) query.getSingleResult();

        //check if user is a partner
        query = entityManager.createQuery("SELECT p FROM Partner p WHERE p.username = :username", User.class);
        query.setParameter("username", username);

        if(query.getResultList().size() != 0)
            return (Partner) query.getSingleResult();

        //No user found
        return null;
    }
}
