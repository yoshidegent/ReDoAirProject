package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalTime;
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
    }

    public void importData()
    {
        CountryImporter countryImporter = new CountryImporter();
        countryImporter.importCountries(entityManager);
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
        addresses.add(
            new Address(8l, countryRepository.findCountryByCountryCodeCaseInsensitive("AU"),
                "Sydney"));
        addresses.add(
            new Address(9l, countryRepository.findCountryByCountryCodeCaseInsensitive("US"),
                "New York City"));
        addresses.add(
            new Address(10l, countryRepository.findCountryByCountryCodeCaseInsensitive("UA"),
                "Melbourne"));

        for(Address a : addresses)
        {
              entityManager.merge(a);
        }
    }

    private void importAirlines()
    {
        airlines.add(new Airline(1l, "SN Brussels Airlines", 0.03, "http://logos.skyscnr.com/images/airlines/small/SN.png"));
        airlines.add(new Airline(2l, "Aer Lingus", 0.08, "http://logos.skyscnr.com/images/airlines/small/EI.png"));
        airlines.add(new Airline(3l, "Qatar Airlines", 0.10, "http://logos.skyscnr.com/images/airlines/small/QR.png"));
        airlines.add(new Airline(4l, "Iberia", 0.08, "http://logos.skyscnr.com/images/airlines/small/IB.png"));
        airlines.add(new Airline(5l, "Emirates", 0.03, "http://logos.skyscnr.com/images/airlines/small/EK.png"));
        airlines.add(new Airline(6l, "KLM", 0.08, "http://logos.skyscnr.com/images/airlines/small/KL.png"));
        airlines.add(new Airline(7l, "British Airways", 0.08, "http://logos.skyscnr.com/images/airlines/small/BA.png"));

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
        airports.add(new Airport(9l, "Kingsford Smith", addressRepository.findById(8l)));
        airports.add(new Airport(10l, "JFK", addressRepository.findById(9l)));
        airports.add(new Airport(11l, "Melbourne Airport", addressRepository.findById(10l)));

        for (Airport airport : airports) {
            entityManager.merge(airport);
        }
    }

    private void importFlights()
    {
        flights.add(new Flight(1l, airlineRepository.findById(1l), airportRepository.findById(1l), airportRepository.findById(5l), LocalDate.of(2015,10,16), LocalTime.of(21,00), 200, 500,200, 60,15));
        flights.add(new Flight(2l, airlineRepository.findById(1l), airportRepository.findById(5l), airportRepository.findById(1l), LocalDate.of(2015, 10, 18), LocalTime.of(11, 00), 120,800, 200, 130,15));

        flights.add(new Flight(3l, airlineRepository.findById(3l), airportRepository.findById(1l), airportRepository.findById(7l), LocalDate.of(2015, 10, 22), LocalTime.of(8, 30),110, 500, 350, 99,15));
        flights.add(new Flight(4l, airlineRepository.findById(3l), airportRepository.findById(7l), airportRepository.findById(1l), LocalDate.of(2015, 11, 22), LocalTime.of(8, 30),110, 500,350, 150,15));

        flights.add(new Flight(5l, airlineRepository.findById(2l), airportRepository.findById(1l), airportRepository.findById(9l), LocalDate.of(2015, 10, 22), LocalTime.of(8, 30),110, 250, 100,199,15));
        flights.add(new Flight(6l, airlineRepository.findById(2l), airportRepository.findById(9l), airportRepository.findById(1l), LocalDate.of(2015, 10, 28), LocalTime.of(8, 30),110, 250, 88, 155, 20));

        flights.add(new Flight(7l, airlineRepository.findById(4l), airportRepository.findById(1l), airportRepository.findById(10l), LocalDate.of(2015, 10, 22), LocalTime.of(8, 30),110, 450, 300, 170,15));
        flights.add(new Flight(8l, airlineRepository.findById(4l), airportRepository.findById(10l), airportRepository.findById(6l), LocalDate.of(2015, 10, 19), LocalTime.of(8, 30),110, 450, 301, 165,15));

        flights.add(new Flight(9l, airlineRepository.findById(5l), airportRepository.findById(1l), airportRepository.findById(11l), LocalDate.of(2015, 11, 22), LocalTime.of(8, 30),110, 450, 301, 165,15));
        flights.add(new Flight(10l, airlineRepository.findById(5l), airportRepository.findById(11l), airportRepository.findById(1l), LocalDate.of(2015, 12, 22), LocalTime.of(8, 30),110, 450, 301, 165,15));

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

        List<Flight> flightsTrip2 = new ArrayList<>();
        flightsTrip1.add(flightRepository.findById(3l));
        flightsTrip1.add(flightRepository.findById(4l));

        trips.add(new Trip(2l, flightsTrip2,"Le Méridien", 80, 10, airportRepository.findById(7l)));


        List<Flight> flightsTrip3 = new ArrayList<>();
        flightsTrip1.add(flightRepository.findById(5l));
        flightsTrip1.add(flightRepository.findById(6l));

        trips.add(new Trip(3l, flightsTrip3,"Le Méridien", 80, 20, airportRepository.findById(9l)));


        List<Flight> flightsTrip4 = new ArrayList<>();
        flightsTrip1.add(flightRepository.findById(7l));
        flightsTrip1.add(flightRepository.findById(8l));

        trips.add(new Trip(4l, flightsTrip4,"Le Méridien", 80, 12, airportRepository.findById(10l)));


        List<Flight> flightsTrip5 = new ArrayList<>();
        flightsTrip1.add(flightRepository.findById(9l));
        flightsTrip1.add(flightRepository.findById(10l));

        trips.add(new Trip(5l, flightsTrip5,"Another hotel :p", 130, 30, airportRepository.findById(11l)));


        for (Trip t : trips) {
            entityManager.merge(t);
        }
    }
}
