package com.realdolmen.redoairproject.entities;

import javax.ejb.Local;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {

    /**
     * Class fields
     */
    @ManyToMany
    private List<Flight> flightList;
    private String hotel;
    private double priceHotelPerNightPerPerson;
    @Transient
    private int numberOfNights;
    @ManyToOne
    private Airport endDestination;


    /**
     * Constructor
     */
    public Trip() {
    }


    public Trip(Long id, List<Flight> flightList, String hotel, double priceHotelPerNightPerPerson, int numberOfNights, Airport endDestination) {
        super(id);
        this.flightList = flightList;
        this.hotel = hotel;
        this.priceHotelPerNightPerPerson = priceHotelPerNightPerPerson;
        this.numberOfNights = numberOfNights;
        this.endDestination = endDestination;
    }

    /**
     * Bussiness Methods
     */
    public double calculateTotalPrice(int numberOfPersons) {
        double priceForAllFlights = 0;

        for (Flight f : flightList) {
            priceForAllFlights = priceForAllFlights + f.getPricePerSeatForPassenger();
        }

        double pricePerPerson = priceHotelPerNightPerPerson*numberOfNights + priceForAllFlights;
        return pricePerPerson*numberOfPersons;
    }
    public int calculateDurationOfTrip() {
        LocalDate departureDate = flightList.get(0).getDepartureDate();
        LocalDate backHomeDate = flightList.get(0).getDepartureDate();

        for (Flight flight : flightList) {
            if(flight.getDepartureDate().isBefore(departureDate))    {
                departureDate = flight.getDepartureDate();
            }
        }

        for (Flight flight : flightList) {
            if(flight.getDepartureDate().isAfter(backHomeDate)) {
                backHomeDate = flight.getDepartureDate();

                LocalDateTime arrivalDateCheck =  LocalDateTime.of(backHomeDate.getYear(), backHomeDate.getMonthValue(), backHomeDate.getDayOfMonth(), flight.getDepartureTime().getHour(), flight.getDepartureTime().getMinute());
                arrivalDateCheck = arrivalDateCheck.plusMinutes(flight.getFlightDurationInMinutes());

                if(arrivalDateCheck.getDayOfYear() > backHomeDate.getDayOfYear())   {
                    backHomeDate.plusDays(1);
                }

            }
        }

        long l = backHomeDate.toEpochDay() - departureDate.toEpochDay();
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

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
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
            ", numberOfNights=" + numberOfNights +
            ", endDestination=" + endDestination +
            '}';
    }
}
