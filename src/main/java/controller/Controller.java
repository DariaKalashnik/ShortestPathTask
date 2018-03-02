package controller;

import models.City;
import models.Flight;
import utils.Utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private String mElementName;
    List<City> listCities = new ArrayList<>();
    List<Flight> listFlights = new ArrayList<>();


    private static final String FLIGHT_ELEMENT_NAME = "flight";
    private static final String CITY_ELEMENT_NAME = "city";


    public void start() {
        readXMLCities();
        readXMLFlights();
        printData();
    }

    private void printData() {
        for (Flight flight : listFlights) {
            System.out.println(flight.toString());
        }
        for (City city : listCities) {
            System.out.println(city.toString());
        }
    }

    private XMLStreamReader openXml(String fileName) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        FileReader fileReader = new FileReader(fileName);
        return inputFactory.createXMLStreamReader(fileReader);
    }

    private void readXMLFlights() {
        try {
            // Create a stream reader object
            XMLStreamReader reader = openXml("flights.xml");
            // Read the flights.xml file
            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        mElementName = reader.getLocalName();
                        if (mElementName.equals(FLIGHT_ELEMENT_NAME)) {
                            Utils.readFlightXMLelements(reader, listFlights);
                        }
                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        mElementName = reader.getLocalName();
                        if (mElementName.equals(FLIGHT_ELEMENT_NAME)) {
                            Utils.readFlightXMLelements(reader, listFlights);
                        }
                        break;
                }
                reader.next();
            }
            reader.close();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }


    private void readXMLCities() {
        try {
            // Create a stream reader object
            XMLStreamReader reader = openXml("flights.xml");
            // Read the flights.xml file
            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        mElementName = reader.getLocalName();
                        if (mElementName.equals(CITY_ELEMENT_NAME)) {
                            Utils.readCityXMLelements(reader, listCities);
                        }
                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        mElementName = reader.getLocalName();
                        if (mElementName.equals(CITY_ELEMENT_NAME)) {
                            Utils.readCityXMLelements(reader, listCities);
                        }
                        break;
                }
                reader.next();
            }
            reader.close();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }


}
