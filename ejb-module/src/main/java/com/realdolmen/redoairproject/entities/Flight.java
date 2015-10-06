package com.realdolmen.redoairproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Flight extends AbstractEntity {
    private static final Double PROFIT_MARGIN = 1.05;

    /**
     * Class fields
     */
    @ManyToOne
    private Airline airline;

    @ManyToOne
    private Airport origin;
    @ManyToOne
    private Airport destination;


    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    private int flightDurationInMinutes;

    private int seatsAvailable;

    private double pricePerSeat;

    private int seatsThresholdForDiscount;
    @Transient
    private double pricePerSeatForPassenger;



    /**
     * Constructor
     */
    public Flight() {
    }

    public Flight(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public Flight(Long id, Airline airline, Airport origin, Airport destination, Date departureDate, Date departureTime, int flightDurationInMinutes, int seatsAvailable, double pricePerSeat, int seatsThresholdForDiscount) {
        super(id);
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.flightDurationInMinutes = flightDurationInMinutes;
        this.seatsAvailable = seatsAvailable;
        this.pricePerSeat = pricePerSeat;
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
        this.pricePerSeatForPassenger = this.calculatePriceforPassenger();
    }

    /**
     * Bussiness Logic
     */
    public LocalDateTime calculateArrivalTime(LocalDateTime departure, double duration )  {
//        this.departureTime;
//        this.duration;
        return null;
    }

    public double calculatePriceforPassenger()  {
        return this.pricePerSeatForPassenger = this.pricePerSeat * PROFIT_MARGIN;
    }

    public void overridePriceForPassenger(double price)   {
        this.pricePerSeatForPassenger = price;
    }


    public double calculateTotalProfitMargin(int numberOfBookings)  {
        if(numberOfBookings > this.getSeatsThresholdForDiscount())  {
            return numberOfBookings * (this.pricePerSeatForPassenger - (this.pricePerSeat * (1-this.getAirline().getDiscountVolumeSales())));
        } else {
            return numberOfBookings * (this.pricePerSeatForPassenger - this.pricePerSeat);
        }
    }

    /**
     * Getters & Setters
     */
    public static Double getProfitMargin() {
        return PROFIT_MARGIN;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public double getFlightDurationInMinutes() {
        return flightDurationInMinutes;
    }

    public void setFlightDurationInMinutes(int duration) {
        this.flightDurationInMinutes = duration;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public int getSeatsThresholdForDiscount() {
        return seatsThresholdForDiscount;
    }

    public void setSeatsThresholdForDiscount(int seatsThresholdForDiscount) {
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDay) {
        this.departureDate = departureDay;
    }

    public double getPricePerSeatForPassenger() {
        return this.pricePerSeatForPassenger;
    }
}
