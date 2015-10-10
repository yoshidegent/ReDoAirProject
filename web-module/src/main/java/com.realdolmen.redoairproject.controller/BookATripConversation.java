package com.realdolmen.redoairproject.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BookATripConversation {

    @Inject
    private Conversation conversation;

    @Inject
    DestinationsController destinationsController;


    @Inject
    WorldmapController worldmapController;



    public String startConversation() {
        conversation.begin();
        return "worldmap?faces-redirect=true";
    }

//    public String createPassenger() {
//        passengerController.createPassenger();
//        return "destinations";
//    }
//
//    public String addTicketForThisPassenger(int pid)   {
//        ticketController.addTicketForPassenger(pid);
//        return "page3toconfirmation";
//    }

    public String endConversationAndGoToHomePage()  {
        conversation.end();
        return "index";
    }



}
