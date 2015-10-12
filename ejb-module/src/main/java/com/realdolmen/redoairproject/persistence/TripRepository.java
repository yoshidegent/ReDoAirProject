package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.converter.LocalDatePersistenceConverter;
import com.realdolmen.redoairproject.entities.Flight;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import com.realdolmen.redoairproject.entities.Country;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@LocalBean @Stateless public class TripRepository extends GenericRepository<Trip>
    implements ITripRepository, Serializable {

    public TripRepository() {
        super(Trip.class);
    }

    @Override public List<Trip> findTripsByCountry(Country country) {
        TypedQuery<Trip> query = entityManager.createQuery(
            "SELECT t FROM Trip t WHERE t.endDestination.address.country.countryCode = :countryCode",
            Trip.class);
        query.setParameter("countryCode", country.getCountryCode());
        return query.getResultList();
    }


    @Override public List<Country> findAllCountriesFromTrips() {
        TypedQuery<Country> query = entityManager
            .createQuery("SELECT t.endDestination.address.country FROM Trip t WHERE (select count(f.id) from t.flightList f) > 0", Country.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findAllDestinationNames()   {
        return entityManager.createQuery("SELECT distinct t.endDestination.address.country.country FROM Trip t order by t.endDestination.address.country.country").getResultList();
    }

    public List<Trip> findValidTrips(Country country, Date periodStart, Date periodEnd,
        int numberOfPassengers) {

        List<Trip> tripsByCountry = findTripsByCountry(country);

        List<Trip> resultingTrips = new ArrayList<>();

        for (Trip trip : tripsByCountry) {

            LocalDate beginDate = trip.calculateBeginDate();
            LocalDate endDate = trip.calculateEndDate();

            LocalDatePersistenceConverter localDatePersistenceConverter =
                new LocalDatePersistenceConverter();

            LocalDate localBeginDate = localDatePersistenceConverter
                .convertToEntityAttribute(new java.sql.Date(periodStart.getTime()));
            LocalDate localEndDate = localDatePersistenceConverter
                .convertToEntityAttribute(new java.sql.Date(periodEnd.getTime()));

            if (beginDate.isAfter(localBeginDate) && endDate.isBefore(localEndDate)
                && numberOfPassengers <= trip.calculateNumberOfPlacesAvailableForAllFlights()
                && trip.getFlightList() != null && trip.getFlightList().size() > 0)
            {
                resultingTrips.add(trip);
            }
        }

        return resultingTrips;
    }
}
