package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.*;

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

        AirlineRepository airlineRepository = new AirlineRepository();

        airlineRepository.entityManager = entityManager;

//        Partner partnerBrussels = new Partner("PartnerBA", "password");
//        partnerBrussels.setAirline(airlineRepository.findAirlineByNameCaseInsensitive("SN Brussels Airlines"));
//        partnerBrussels = entityManager.merge(partnerBrussels);
//
//        Partner partnerKlm = new Partner("PartnerKLM", "password");
//        partnerKlm.setAirline(airlineRepository.findAirlineByNameCaseInsensitive("KLM"));
//        partnerKlm = entityManager.merge(partnerKlm);
//
//        Partner partnerEmirates = new Partner("PartnerEmirates", "password");
//        partnerEmirates.setAirline(airlineRepository.findAirlineByNameCaseInsensitive("KLM"));
//        partnerEmirates = entityManager.merge(partnerEmirates);



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

    public void setPasswordForPartners(EntityManagerFactory factory)    {
        UserRepository userRepository = new UserRepository();
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        userRepository.entityManager = entityManager;
        User partnerBrusselsAirlines = userRepository.getUserByUsername("PartnerBrusselsAirlines");
        partnerBrusselsAirlines.setHashedPassword(partnerBrusselsAirlines.hashPassword("password"));
        userRepository.createOrUpdate(partnerBrusselsAirlines);

        User partnerKLM = userRepository.getUserByUsername("partnerKLM");
        partnerKLM.setHashedPassword(partnerKLM.hashPassword("password"));
        userRepository.createOrUpdate(partnerKLM);

        User partnerEmirates = userRepository.getUserByUsername("partnerEmirates");
        partnerEmirates.setHashedPassword(partnerEmirates.hashPassword("password"));
        userRepository.createOrUpdate(partnerEmirates);


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
