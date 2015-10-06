package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Flight;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DataImporter
{
    private EntityManager entityManager;

    private List<Flight> flights = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    private CountryRepository countryRepository = new CountryRepository();

    public DataImporter(EntityManager entityManager) {
        this.entityManager = entityManager;
        countryRepository.entityManager = entityManager;
    }

    public void importData()
    {
        CountryImporter countryImporter = new CountryImporter();
        countryImporter.importCountries(entityManager);

        importAddresses();
    }

    private void importAddresses()
    {
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("BE"), "Brussels"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("BE"), "Charleroi"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("FR"), "Paris"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("FR"), "Lyon"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("GB"), "London"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("IE"), "Dublin"));
        addresses.add(new Address(countryRepository.findCountryByCountryCodeCaseInsensitive("NL"), "Amsterdam"));

        for(Address a : addresses)
        {
              entityManager.merge(a);
        }
    }

    private void importFlights()
    {
        flights.add(new Flight());
        flights.add(new Flight());
        flights.add(new Flight());
        flights.add(new Flight());
        flights.add(new Flight());
        flights.add(new Flight());

        for (Flight f : flights)
        {
            entityManager.merge(f);
        }
    }
}
