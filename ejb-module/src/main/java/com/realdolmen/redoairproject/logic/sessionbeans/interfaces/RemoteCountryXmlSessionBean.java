package com.realdolmen.redoairproject.logic.sessionbeans.interfaces;

import org.jdom2.Document;

import javax.ejb.Remote;
import java.util.Map;

@Remote
public interface RemoteCountryXmlSessionBean {
    Map<String, String> fetchAllCountriesWithCountryCode();

    String performGetCountryXml();
    Map<String, String> fetchCountriesFromXml(Document document);
}
