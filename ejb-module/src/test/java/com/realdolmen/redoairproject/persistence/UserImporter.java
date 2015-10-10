package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Partner;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.ReDoEmployee;
import com.realdolmen.redoairproject.entities.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserImporter {

    public void importUsers(EntityManagerFactory entityManagerFactory)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        //Clear user tables
        entityManager.createQuery("DELETE from Passenger p").executeUpdate();
        entityManager.createQuery("DELETE from Partner p").executeUpdate();
        entityManager.createQuery("DELETE from ReDoEmployee e").executeUpdate();


        User yoshi = new Passenger("yoshidegent", "password");
        yoshi = entityManager.merge(yoshi);

        User evelyne = new Passenger("evelynewauters", "password");
        evelyne = entityManager.merge(evelyne);

        User partner = new Partner("Partner", "password");
        partner = entityManager.merge(partner);

        User reDoEmployee = new ReDoEmployee("ReDoEmployee", "password");
        reDoEmployee = entityManager.merge(reDoEmployee);


        if(entityManager.getTransaction() != null) {
            if(entityManager.getTransaction().getRollbackOnly()) {
                entityManager.getTransaction().rollback();
            } else {
                entityManager.getTransaction().commit();
            }
        }

        entityManager.close();
    }
}
