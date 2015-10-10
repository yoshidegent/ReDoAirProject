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

    private String feedbackMessage;

    @Inject UserRepository userRepository;

    //redirecten moet nog juister gebeuren dan gewoon return "worldmap"

    public String createPassenger() {
        userRepository.createOrUpdate(passenger);
        return "worldmap";
    }

    public String logInUser() {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            feedbackMessage = "Username " + username + " was not found. Please register or try again.";
            return "";

        } else {
            if(user.checkPasswordIsValid(password))
            {
                if(user instanceof Partner || user instanceof ReDoEmployee)
                    return "flightsall";
                else
                    return "worldmap";
            }
            else
            {
                feedbackMessage = "The password and username did not match. Please register or try again.";
                return "";
            }

        }
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

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
