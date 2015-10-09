package com.realdolmen.redoairproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Country implements Serializable{

    @Id
    String countryCode = "";
    String country = "";

    public Country() {
    }

    public Country(String countryCode, String country) {
        this.countryCode = countryCode;
        this.country = country;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return !(countryCode != null ? !countryCode.equals(country.countryCode) : country.countryCode != null);

    }
}
