//package com.realdolmen.redoairproject.logic.sessionbeans;
//
//
//import com.realdolmen.redoairproject.logic.sessionbeans.interfaces.RemoteCountryXmlSessionBean;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Stateless
//@LocalBean
//public class CountryXmlSessionBean implements RemoteCountryXmlSessionBean
//{
//    private final static String COUNTRY_XML_URL = "http://api.worldbank.org/countries";
//
//    @Override
//    public Map<String, String> fetchAllCountriesWithCountryCode() {
//        Map<String, String> countryCodeMap = new HashMap<>();
//
//        SAXBuilder saxBuilder = new SAXBuilder();
//        Document document = null;
//        try {
//            document = saxBuilder.build(performGetCountryXml());
//
//            countryCodeMap = fetchCountriesFromXml(document);
//
//        } catch (JDOMException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return  countryCodeMap;
//    }
//
//    @Override
//    public Map<String, String> fetchCountriesFromXml(Document document) {
//        Map<String, String> countryCodeMap = new HashMap<>();
//
//
//        Element rootElement = document.getRootElement();
//
//
//        List<Element> elements = rootElement.getChildren();
//
//        for (Element e : elements)
//        {
//            String country = "";
//            String countryCode = "";
//
//            Element countryElement = e.getChild("name", rootElement.getNamespace());
//            Element countryCodeElement = e.getChild("iso2Code", rootElement.getNamespace());
//
//            country = countryElement.getValue();
//            countryCode = countryCodeElement.getValue();
//
//            countryCodeMap.put(countryCode, country);
//        }
//
//        return countryCodeMap;
//    }
//
//    @Override
//    public String performGetCountryXml()
//    {
//        StringBuilder result = new StringBuilder();
//
//        URL url = null;
//        try {
//            url = new URL(COUNTRY_XML_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            rd.close();
//            return result.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return  "";
//    }
//}
