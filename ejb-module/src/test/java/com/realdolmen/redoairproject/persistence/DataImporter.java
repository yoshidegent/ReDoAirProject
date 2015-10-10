package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DataImporter
{
    private EntityManagerFactory entityManagerFactory;

    public DataImporter(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void importData()
    {
        CountryImporter countryImporter = new CountryImporter();
        countryImporter.importCountries(entityManagerFactory);

        UserImporter userImporter = new UserImporter();
        userImporter.importUsers(entityManagerFactory);
    }
}
