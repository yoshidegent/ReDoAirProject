package com.realdolmen.redoairproject.entities;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Address extends AbstractEntity
{

    private final int COUNTRY_CODE_INDEX = 0;
    private final int COUNTRY_INDEX = 1;

    @OneToOne
    private Country country;

    private String city;

    public Address() {
    }

    public void setCountryCodeViaCountry(String country) {

    }

    public void setCity(String city) {
        this.city = city;
    }
}
