package com.realdolmen.redoairproject.entities;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
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

    @OneToOne
    private Country country;

    private String city;

    public Address() {
    }

    public Address(Country country, String city)
    {
        this.country = country;
        this.city = city;
    }

    public void setCountryCodeViaCountry(String country) {
    }

    public void setCity(String city) {
        this.city = city;
    }
}
