package com.realdolmen.redoairproject.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Address extends AbstractEntity
{
    @Transient
    private final int COUNTRY_CODE_INDEX = 0;
    @Transient
    private final int COUNTRY_INDEX = 1;

    @ManyToOne
    private Country country;

    private String city;

    public Address() {
    }

    public Address(Long id, Country country, String city) {
        super(id);
        this.country = country;
        this.city = city;
    }

    public void setCountryCodeViaCountry(String country) {
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }
}
