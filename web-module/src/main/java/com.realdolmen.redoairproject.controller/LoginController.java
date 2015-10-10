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

    private Passenger passenger;
    private User user;
    private String username;
    private String password;

    @Inject UserRepository userRepository;

    //redirecten moet nog juister gebeuren dan gewoon return "worldmap"

    public String createPassenger() {
        userRepository.createOrUpdate(passenger);
        return "worldmap";
    }

    public String logInUser(String userName) {
        User user = userRepository.getUserByUsername(userName);

        if (user == null) {
            return "";

        } else {
            return "";
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
