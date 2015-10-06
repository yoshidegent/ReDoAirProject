package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Country;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryImporter {

    private static final Logger LOG = LoggerFactory.getLogger(CountryImporter.class);

    public void importCountries(EntityManager entityManager)
    {
        //Temporary map to store the key value pairs of countries and their country code
        Map<String, String> countryCodeMap = fetchAllCountriesWithCountryCode();


        int numberOfCountriesImported = 0;
        LOG.debug("Imported countries:");
        for(Map.Entry<String, String> e : countryCodeMap.entrySet())
        {
            numberOfCountriesImported++;

            Country country = new Country();
            country.setCountry(e.getValue());
            country.setCountryCode(e.getKey());

            country = entityManager.merge(country);
            LOG.debug(country.getCountryCode() + ", " + country.getCountry());

            numberOfCountriesImported++;
            LOG.debug("Number of countries imported: " + numberOfCountriesImported);
        }
    }


    private Map<String, String> fetchAllCountriesWithCountryCode() {
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

    private Map<String, String> fetchCountriesFromXml(Document document) {
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
