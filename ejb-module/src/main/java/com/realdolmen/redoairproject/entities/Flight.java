package com.realdolmen.redoairproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight extends AbstractEntity {

    private static final Double PROFIT_MARGIN = 0.05;
    /**
     * Class fields
     */
    private String airline;                 //enums van maken?

    private String origin;
    private String destination;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTime;
    private double duration;
    private int seatsAvailable;

    private Double price;
    private double discountVolumeSales;
    private int seatsThresholdForDiscount;



    /**
     * Constructor
     */
    public Flight() {
    }

    /**
     * Bussiness Logic
     */
    public LocalDateTime getArrivalTime(LocalDateTime departure, double duration )  {
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
