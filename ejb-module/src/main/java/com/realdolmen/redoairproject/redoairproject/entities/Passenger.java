package com.realdolmen.redoairproject.redoairproject.entities;

import com.realdolmen.ex.domain.Address;
import com.realdolmen.ex.domain.CreditCard;
import com.realdolmen.ex.domain.PassengerType;
import com.realdolmen.ex.domain.Ticket;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YDEAX41 on 9/09/2015.
 */
@Entity
public class Passenger extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(updatable = false)
    private String ssn;

    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;

    private Integer frequentFlyerMiles;

    //@NotNull
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private Date dateOfBirth;

    @Transient
    private Integer age;

    //@NotNull
    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date flightTime;

    @ElementCollection
    private List<CreditCard> creditCardList;

    @Embedded
    private Address address;

    @ElementCollection
    private List<String> preferences;

    @OneToMany
    private List<Ticket> ticketList;

    private Date dateLastUpdated;

    public long getId() {
        return id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public void setFrequentFlyerMiles(Integer frequentFlyerMiles) {
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    //@PostLoad
    //@PostPersist
    //@PostUpdate
    private void setAge()
    {
        this.age = calculateAge(this.dateOfBirth);
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public List<CreditCard> getCreditCardList() {
        if(creditCardList == null)
            creditCardList = new ArrayList<>();
        return creditCardList;
    }

    public void setCreditCardList(List<CreditCard> creditCard) {
        this.creditCardList = creditCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPreferences() {
        if(preferences == null)
            preferences = new ArrayList<>();
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public List<Ticket> getTicketList() {
        if(ticketList == null)
            ticketList = new ArrayList<>();
        return ticketList;
    }

    public void setTicketList(List<Ticket> tickets) {
        this.ticketList = tickets;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public Integer calculateAge(Date dateOfBirth) {
        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.setTime(dateOfBirth);
        //setup calNow as today.
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
        //calculate age in years.
        int ageYr = (calNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR));
        // calculate additional age in months, possibly adjust years.
        int ageMo = (calNow.get(Calendar.MONTH) - calendarBirth.get(Calendar.MONTH));

        if (ageMo < 0) {
            //adjust years by subtracting one
            ageYr--;
        }
        return ageYr;
    }

    @PrePersist
    @PreUpdate
    public void dateLastUpdated()
    {
        this.dateLastUpdated = new Date();
    }
}
