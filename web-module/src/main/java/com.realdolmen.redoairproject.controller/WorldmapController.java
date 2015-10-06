package com.realdolmen.redoairproject.controller;

import com.google.gson.Gson;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.persistence.CountryRepository;
import com.realdolmen.redoairproject.persistence.TripRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class WorldmapController
{
    @Inject
    private TripRepository tripRepository;

    private List<String> countryNameList = new ArrayList<>();

    public String getAvailableCountries()
    {
        List<Country> countries = tripRepository.findAllCountriesFromTrips();
        for(Country c : countries)
        {
            countryNameList.add(c.getCountry());
        }
        return "worldmap";
    }

    public List<String> getCountryNameList() {
        return countryNameList;
    }

    public void setCountryNameList(List<String> countryNameList) {
        this.countryNameList = countryNameList;
    }

    public String getGsonCountries()
    {
        return new Gson().toJson(countryNameList);
    }
}
