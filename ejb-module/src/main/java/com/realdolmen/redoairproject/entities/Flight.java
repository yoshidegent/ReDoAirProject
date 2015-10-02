package com.realdolmen.redoairproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Flight extends AbstractEntity {

    private static final Double PROFIT_MARGIN = 0.05;
    /**
     * Class fields
     */
    @ManyToOne
    private Airline airline;

    @OneToOne
    private Airport origin;
    @OneToOne
    private Airport destination;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    private double duration;

    private int seatsAvailable;

    private double price;
    private double discountVolumeSales;
    private int seatsThresholdForDiscount;


    /**
     * Constructor
     */
    public Flight() {
    }

    public Flight(Long id, Airline airline, Airport origin, Airport destination, Date departureTime, double duration, int seatsAvailable, double price, double discountVolumeSales, int seatsThresholdForDiscount) {
        super(id);
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
        this.discountVolumeSales = discountVolumeSales;
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
    }

    /**
     * Bussiness Logic
     */
    public LocalDateTime calculateArrivalTime(LocalDateTime departure, double duration )  {
//        this.departureTime;
//        this.duration;
        return null;
    }

    public Double calculatePrice()  {
        return this.price * PROFIT_MARGIN;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountVolumeSales() {
        return discountVolumeSales;
    }

    public void setDiscountVolumeSales(double discountVolumeSales) {
        this.discountVolumeSales = discountVolumeSales;
    }

    public int getSeatsThresholdForDiscount() {
        return seatsThresholdForDiscount;
    }

    public void setSeatsThresholdForDiscount(int seatsThresholdForDiscount) {
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
    }
}
