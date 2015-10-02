package com.realdolmen.redoairproject;

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


        public List<Flight> getAllFlights() {
            return repository.findAll();
        }


        public void removeFlight(Flight flight) {
            repository.delete(flight);
        }


}
