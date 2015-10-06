package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.*;

import javax.persistence.EntityManager;
import java.util.*;

public class DataImporter
{
    private EntityManager entityManager;

    private List<Flight> flights = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Airline> airlines = new ArrayList<>();
    private List<Airport> airports = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    private CountryRepository countryRepository = new CountryRepository();
    private AddressRepository addressRepository = new AddressRepository();
    private AirlineRepository airlineRepository = new AirlineRepository();
    private AirportRepository airportRepository = new AirportRepository();
    private TripRepository tripRepository = new TripRepository();
    private FlightRepository flightRepository = new FlightRepository();

    public DataImporter(EntityManager entityManager) {
        this.entityManager = entityManager;
        countryRepository.entityManager = entityManager;
        addressRepository.entityManager = entityManager;
        airlineRepository.entityManager = entityManager;
        airportRepository.entityManager = entityManager;
        tripRepository.entityManager = entityManager;
        flightRepository.entityManager = entityManager;
    }

    public void importData()
    {
        CountryImporter countryImporter = new CountryImporter();
        countryImporter.importCountries(entityManager);

        importAddresses();
        importAirlines();
        importAirports();
        importFlights();
        importTrips();
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
        airports.add(new Airport(2l, "Brussels South", addressRepository.findById(2l)));
        airports.add(new Airport(3l, "Orly", addressRepository.findById(3l)));
        airports.add(new Airport(4l, "Charles De Gaulle", addressRepository.findById(3l)));
        airports.add(new Airport(5l, "Lyon-Saint Exupéry", addressRepository.findById(4l)));
        airports.add(new Airport(6l, "Heathrow", addressRepository.findById(5l)));
        airports.add(new Airport(7l, "Dublin", addressRepository.findById(6l)));
        airports.add(new Airport(8l, "Schiphol", addressRepository.findById(7l)));

        for (Airport airport : airports) {
            entityManager.merge(airport);
        }
    }

    private void importFlights()
    {



        flights.add(new Flight(1l, airlineRepository.findById(1l), airportRepository.findById(1l), airportRepository.findById(5l), 500,130,15));
        flights.add(new Flight(2l, airlineRepository.findById(1l), airportRepository.findById(5l), airportRepository.findById(1l), 500,130,15));
        flights.add(new Flight(3l, airlineRepository.findById(3l), airportRepository.findById(1l), airportRepository.findById(6l), 500,130,15));


        for (Flight f : flights)
        {
            entityManager.merge(f);
        }
    }

    private void importTrips()
    {
        List<Flight> flightsTrip1 = new ArrayList<>();
        flightsTrip1.add(flightRepository.findById(1l));
        flightsTrip1.add(flightRepository.findById(2l));

        trips.add(new Trip(1l, flightsTrip1,"Le Méridien", 80, 8, airportRepository.findById(5l)));

        for (Trip t : trips) {
            entityManager.merge(t);
        }
    }
}
