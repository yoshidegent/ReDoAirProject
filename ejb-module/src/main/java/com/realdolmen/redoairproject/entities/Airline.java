package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airline extends AbstractEntity {

    /**
     * Class fields
     */
    private String name;
    private double discountVolumeSales;
    private String imageUrl;



    /**
     * Constructor
     */

    public Airline() {
    }


    public Airline(Long id, String name, double discountVolumeSales, String imageUrl) {
        super(id);
        this.name = name;
        this.discountVolumeSales = discountVolumeSales;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
