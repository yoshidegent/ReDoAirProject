package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryImportTest extends PersistenceTest{

    private static final Logger LOG = LoggerFactory.getLogger(CountryImportTest.class);

    @Inject
    private CountryRepository countryRepository;

    @Test
    public void importCountries()
    {
        countryRepository = new CountryRepository();

        Map<String, String> countryCodeMap = fetchAllCountriesWithCountryCode();
        int count = 0;
        for(Map.Entry<String, String> e : countryCodeMap.entrySet())
        {
            count++;
            EntityManager entityManager = entityManager();
            Country country = new Country();
            country.setCountry(e.getValue());
            country.setCountryCode(e.getKey());
            countryRepository.entityManager = entityManager();
            countryRepository.createOrUpdate(country);
            System.out.println(e.getKey() + ", " + e.getValue());
        }

        Assert.assertTrue(countryRepository.findAll().size() == count);
    }


    public Map<String, String> fetchAllCountriesWithCountryCode() {
        Map<String, String> countryCodeMap = new HashMap<>();

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            document = saxBuilder.build(new FileInputStream("./countries.xml"));

            countryCodeMap = fetchCountriesFromXml(document);

        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }

        return  countryCodeMap;
    }

    public Map<String, String> fetchCountriesFromXml(Document document) {
        Map<String, String> countryCodeMap = new HashMap<>();


        Element rootElement = document.getRootElement();


        List<Element> elements = rootElement.getChildren();

        for (Element e : elements)
        {
            String country = "";
            String countryCode = "";

            Element countryElement = e.getChild("name", rootElement.getNamespace());
            Element countryCodeElement = e.getChild("iso2Code", rootElement.getNamespace());

            country = countryElement.getValue();
            countryCode = countryCodeElement.getValue();

            countryCodeMap.put(countryCode, country);
        }

        return countryCodeMap;
    }
}
