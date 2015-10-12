package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Booking;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.User;
import com.realdolmen.redoairproject.persistence.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewtax45 on 12/10/2015.
 */

@Named
@RequestScoped
public class PassengerController {

    private Passenger passenger = new Passenger();
    private String passwordToHash;


    @Inject
    UserRepository userRepository;

    public String createPassenger() {
        byte[] hashPassword = passenger.hashPassword(passwordToHash);
        passenger.setHashedPassword(hashPassword);
        userRepository.createOrUpdate(passenger);
        return "worldmap";
    }



    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getPasswordToHash() {
        return passwordToHash;
    }

    public void setPasswordToHash(String passwordToHash) {
        this.passwordToHash = passwordToHash;
    }
}
