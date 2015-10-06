package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Flight;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DataImporter
{
    private EntityManager entityManager;

    private List<Flight> flights = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Airline> airlines = new ArrayList<>();
    private List<Airport> airports = new ArrayList<>();

    private CountryRepository countryRepository = new CountryRepository();
    private AddressRepository addressRepository = new AddressRepository();

    public DataImporter(EntityManager entityManager) {
        this.entityManager = entityManager;
        countryRepository.entityManager = entityManager;
    }

    public void importData()
    {
        CountryImporter countryImporter = new CountryImporter();
        countryImporter.importCountries(entityManager);

        importAddresses();
        importAirlines();
    }

    private void importAddresses()
    {
        addresses.add(new Address(1l, countryRepository.findCountryByCountryCodeCaseInsensitive("BE"), "Brussels"));
        addresses.add(new Address(2l, countryRepository.findCountryByCountryCodeCaseInsensitive("BE"), "Charleroi"));
        addresses.add(new Address(3l, countryRepository.findCountryByCountryCodeCaseInsensitive("FR"), "Paris"));
        addresses.add(new Address(4l, countryRepository.findCountryByCountryCodeCaseInsensitive("FR"), "Lyon"));
        addresses.add(new Address(5l, countryRepository.findCountryByCountryCodeCaseInsensitive("GB"), "London"));
        addresses.add(new Address(6l, countryRepository.findCountryByCountryCodeCaseInsensitive("IE"), "Dublin"));
        addresses.add(new Address(7l, countryRepository.findCountryByCountryCodeCaseInsensitive("NL"), "Amsterdam"));

        for(Address a : addresses)
        {
              entityManager.merge(a);
        }
    }

    private void importAirlines()
    {
        airlines.add(new Airline(1l, "SN Brussels Airlines", 0.03));
        airlines.add(new Airline(2l, "Aer Lingus", 0.08));
        airlines.add(new Airline(3l, "Qatar Airlines", 0.10));

        for (Airline a : airlines) {
            entityManager.merge(a);
        }
    }


    private void importAirports()
    {
        airports.add(new Airport(1l, "Zaventem", addressRepository.findById(1l)));
        airports.add(new Airport(1l, "Zaventem", addressRepository.findById(1l)));
        airports.add(new Airport(1l, "Zaventem", addressRepository.findById(1l)));
        airports.add(new Airport(1l, "Zaventem", addressRepository.findById(1l)));

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
