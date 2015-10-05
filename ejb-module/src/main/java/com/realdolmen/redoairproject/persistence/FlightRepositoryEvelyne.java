package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class FlightRepositoryEvelyne {

        @PersistenceContext
        EntityManager entityManager;


        public List<Flight> findAll() {
            return entityManager.createQuery("select p from Passenger p", Flight.class).getResultList();
        }

    }
