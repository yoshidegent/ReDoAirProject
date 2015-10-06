package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import org.junit.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YDEAX41 on 6/10/2015.
 */
public class CountryRepositoryTest extends PersistenceTest
{
    private Map<String, String> testCountries;

    @Inject
    private CountryRepository countryRepository;

    @Before
    public void before()
    {
        countryRepository = new CountryRepository();
        countryRepository.entityManager = entityManager();

        testCountries = new HashMap<>();
        testCountries.put("BE", "Belgium");
        testCountries.put("AT", "Austria");
        testCountries.put("AU", "Australia");
        testCountries.put("LB", "Lebanon");
        testCountries.put("", "");
    }

    @Test
    public void testFindCountryByCountryCodeCaseInsensitive()
    {
        for(Map.Entry<String, String> e : testCountries.entrySet()) {
            Country country = countryRepository.findCountryByCountryCodeCaseInsensitive(e.getKey());
            Assert.assertEquals(country.getCountry().toLowerCase(), e.getValue().toLowerCase());
        }

        Country country = countryRepository.findCountryByCountryCodeCaseInsensitive("jklqmdmfjkmqdfjk");
        Assert.assertEquals(country.getCountry().toLowerCase(), "");
        Assert.assertEquals(country.getCountryCode().toLowerCase(), "");
    }

    @Test
    public void testFindCountryCodeByCountryCaseInsensitive()
    {
        for(Map.Entry<String, String> e : testCountries.entrySet()) {
            Country country = countryRepository.findCountryCodeByCountryCaseInsensitive(
                e.getValue());
            Assert.assertEquals(country.getCountryCode().toLowerCase(), e.getKey().toLowerCase());
        }

        Country country = countryRepository.findCountryCodeByCountryCaseInsensitive("jqskldmjk");
        Assert.assertEquals(country.getCountryCode().toLowerCase(), "");
        Assert.assertEquals(country.getCountry().toLowerCase(), "");
    }

}
