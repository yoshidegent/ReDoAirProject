package com.realdolmen.redoairproject.entities;

import javax.ejb.Local;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {

    /**
     * Class fields
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Flight> flightList;

    private String hotel;
    private double priceHotelPerNightPerPerson;

    @ManyToOne
    private Airport endDestination;
    @Transient
    private LocalDate beginDate;
    @Transient
    private LocalDate endDate;

    //@Transient
    //private int numberOfNights;

    /**
     * Constructor
     */
    public Trip() {
    }


    public Trip(Long id, List<Flight> flightList, String hotel, double priceHotelPerNightPerPerson, Airport endDestination) {
        super(id);
        this.flightList = flightList;
        this.hotel = hotel;
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
        this.endDestination = endDestination;
        this.beginDate = calculateBeginDate();
        this.endDate = calculateEndDate();
    }

    /**
     * Bussiness Methods
     */

    public int calculateNumberOfPlacesAvailableForAllFlights()  {

        return 0;
    }
    public double calculateTotalPrice(int numberOfPersons) {
        double priceForAllFlights = calculatePriceForAllFlights();

        double pricePerPerson = priceHotelPerNightPerPerson*calculateDurationOfTrip() + priceForAllFlights;
        return pricePerPerson*numberOfPersons;
    }

    public double calculatePriceForAllFlights() {
        double priceForAllFlights = 0;

        for (Flight f : flightList) {
            priceForAllFlights = priceForAllFlights + f.getPricePerSeatForPassenger();
        }
        return priceForAllFlights;
    }


    public LocalDate calculateBeginDate()   {
        if(flightList.size() > 0) {
            LocalDate departureDate = flightList.get(0).getDepartureDate();
            for (Flight flight : flightList) {

                if(flight.getDepartureDate() != null) {
                    if (flight.getDepartureDate().isBefore(departureDate)) {
                        departureDate = flight.getDepartureDate();
                    }
                }
            }
            return departureDate;
        }
        return null;
    }

    public LocalDate calculateEndDate() {
        if(flightList.size() > 0) {
            LocalDate backHomeDate = flightList.get(0).getDepartureDate();

            for (Flight flight : flightList) {
                if(flight.getDepartureDate() != null) {

                    if (flight.getDepartureDate().isAfter(backHomeDate)) {
                        backHomeDate = flight.getDepartureDate();

                        LocalDateTime arrivalDateCheck = LocalDateTime
                            .of(backHomeDate.getYear(), backHomeDate.getMonthValue(),
                                backHomeDate.getDayOfMonth(), flight.getDepartureTime().getHour(),
                                flight.getDepartureTime().getMinute());
                        arrivalDateCheck =
                            arrivalDateCheck.plusMinutes(flight.getFlightDurationInMinutes());

                        if (arrivalDateCheck.getDayOfYear() > backHomeDate.getDayOfYear()) {
                            backHomeDate = backHomeDate.plusDays(1);
                        }
                    }
                }
            }
            return backHomeDate;
        }

        return null;
    }


    public int calculateDurationOfTrip() {
        long l = this.calculateEndDate().toEpochDay() - this.calculateBeginDate().toEpochDay();
        return (int) l;
    }

    /**
     * Getters & Setters
     */

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public double getPriceHotelPerNightPerPerson() {
        return priceHotelPerNightPerPerson;
    }

    public void setPriceHotelPerNightPerPerson(double priceHotelPerNightPerPerson) {
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
    }

    public Airport getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(Airport endDestination) {
        this.endDestination = endDestination;
    }

    @Override public String toString() {
        return "Trip{" +
            "flightList=" + flightList +
            ", hotel='" + hotel + '\'' +
            ", priceHotelPerNightPerPerson=" + priceHotelPerNightPerPerson +
            ", endDestination=" + endDestination +
            '}';
    }

    public LocalDate getBeginDate() {
        return calculateBeginDate();
    }

    public LocalDate getEndDate() {
        return calculateEndDate();
    }
}
