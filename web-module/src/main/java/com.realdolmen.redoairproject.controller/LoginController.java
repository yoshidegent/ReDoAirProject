package com.realdolmen.redoairproject.controller;

import com.realdolmen.redoairproject.entities.Partner;
import com.realdolmen.redoairproject.entities.Passenger;
import com.realdolmen.redoairproject.entities.ReDoEmployee;
import com.realdolmen.redoairproject.entities.User;
import com.realdolmen.redoairproject.persistence.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {

    User passenger = new Passenger();
    String username;
    String password;

    @Inject
    UserRepository userRepository;

    //redirecten moet nog juister gebeuren dan gewoon return "worldmap"

    public String createUser()    {
        userRepository.createOrUpdate(passenger);
        return "worldmap";
    }




    public String logInUser(String userName) {
        User user = userRepository.getUserByUsername(userName);
        if(user == null) {
            return"";
            //liever een message throwen
        }   else    {
        //check password
        }

        return "worldmap";
    }







    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
