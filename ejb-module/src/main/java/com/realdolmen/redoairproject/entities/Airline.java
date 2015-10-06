package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;

@Entity
public class Airline extends AbstractEntity {

    /**
     * Class fields
     */
    private String name;
    private double discountVolumeSales;


    /**
     * Constructor
     */

    public Airline() {
    }


    public Airline(Long id, String name, double discountVolumeSales) {
        super(id);
        this.name = name;
        this.discountVolumeSales = discountVolumeSales;
    }

    /**
     * Bussiness Methods
     */


    /**
     * Getters & Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountVolumeSales() {
        return discountVolumeSales;
    }

    public void setDiscountVolumeSales(double discountVolumeSales) {
        this.discountVolumeSales = discountVolumeSales;
    }
}
