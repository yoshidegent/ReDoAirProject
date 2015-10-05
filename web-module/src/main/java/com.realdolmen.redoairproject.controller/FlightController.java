package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.persistence.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FlightController {

        @Inject
        FlightRepository repository;


        public List<Flight> retrieveAllFlights() {
            List<Flight> all = repository.findAll();
            System.out.println(all.size());
            return all;
        }
}
