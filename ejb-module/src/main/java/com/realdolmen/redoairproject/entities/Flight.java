package com.realdolmen.redoairproject.entities;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalDate departureDate;
    private LocalTime departureTime;

    private int flightDurationInMinutes;



    @NotNull
    private int seatCapacity;
    @NotNull
    private int seatsAvailable;

    @NotNull
    private double pricePerSeat;

    @NotNull
    private int seatsThresholdForDiscount;


    @Transient
    private double pricePerSeatForPassenger;



    /**
     * Constructor
     */
    public Flight() {
    }

    public Flight(Long id, Airline airline, Airport origin, Airport destination, int seatCapacity, int seatsAvailable, double pricePerSeat, int seatsThresholdForDiscount) {
        super(id);
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.seatCapacity = seatCapacity;
        this.seatsAvailable = seatsAvailable;
        this.pricePerSeat = pricePerSeat;
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
    }

    public Flight(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public Flight(Long id, Airline airline, Airport origin, Airport destination, LocalDate departureDate, LocalTime departureTime, int flightDurationInMinutes, int seatCapacity, int seatsAvailable, double pricePerSeat, int seatsThresholdForDiscount) {
        super(id);
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.flightDurationInMinutes = flightDurationInMinutes;
        this.seatCapacity = seatCapacity;
        this.seatsAvailable = seatsAvailable;
        this.setPricePerSeat(pricePerSeat);
        this.seatsThresholdForDiscount = seatsThresholdForDiscount;
        this.pricePerSeatForPassenger = this.calculatePriceforPassenger();
    }

    /**
     * Bussiness Logic
     */
    public LocalTime calculateArrivalTime()  {
        int hours = this.flightDurationInMinutes / 60;
        int minutes = this.flightDurationInMinutes % 60;

        LocalTime arrivalTime = departureTime.plusHours(hours).plusMinutes(minutes);

        return arrivalTime;
    }


    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = Math.abs(pricePerSeat);
    }

    public double calculatePriceforPassenger()  {
        return this.pricePerSeatForPassenger = this.pricePerSeat * PROFIT_MARGIN;
    }

    public void overridePriceForPassenger(double price)   {
        this.pricePerSeatForPassenger = Math.abs(price);
    }


    public double calculateTotalProfitMargin()  {
        int numberOfBookings = seatCapacity - seatsAvailable;
        if(numberOfBookings >= this.getSeatsThresholdForDiscount() && this.getAirline() != null)  {
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

    public int getFlightDurationInMinutes() {
        return flightDurationInMinutes;
    }

    public void setFlightDurationInMinutes(int flightDurationInMinutes) {
        this.flightDurationInMinutes = flightDurationInMinutes;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDay) {
        this.departureDate = departureDay;
    }

    public double getPricePerSeatForPassenger() {
        return this.pricePerSeatForPassenger;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}
