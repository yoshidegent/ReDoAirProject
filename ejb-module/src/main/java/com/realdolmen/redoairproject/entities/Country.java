package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country{

    @Id
    String countryCode;
    String country;


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
