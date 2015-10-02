package com.realdolmen.redoairproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight extends AbstractEntity {

    private static final Double PROFIT_MARGIN = 0.05;
    /**
     * Class fields
     */
    private Airline airline;

    private Airport origin;
    private Airport destination;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTime;
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

    public Flight(Long id, Airline airline, Airport origin, Airport destination, LocalDateTime departureTime, double duration, int seatsAvailable, double price, double discountVolumeSales, int seatsThresholdForDiscount) {
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



}
