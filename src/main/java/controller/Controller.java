package controller;

import engine.DijkstraAlgorithm;
import models.*;
import utils.Utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static final String FLIGHT_ELEMENT_NAME = "flight";
    private static final String CITY_ELEMENT_NAME = "city";

    private String mElementName;
    private List<City> listCities = new ArrayList<>();
    private List<Flight> listFlights = new ArrayList<>();
    private List<String> listAirlines = new ArrayList<>();
    private City smallestCity;
    private City largestCity;


    public void start() {
        readXMLCities();
        readXMLFlights();
        System.out.println();
        printData();
        System.out.println();

        smallestCity = Utils.findSmallestCity(listCities);
        largestCity = Utils.findLargestCity(listCities);
        System.out.println("Smallest city: " + smallestCity);
        System.out.println("Largest city: " + largestCity);

        for (String currentAirLine: listAirlines) {
            System.out.println("\nFlight between the smallest and largest city with " + currentAirLine);
            findRouteBetween(smallestCity, largestCity, currentAirLine);
        }

        System.out.println("\nFlight between the smallest and largest city using all airlines");
        findRouteBetween(smallestCity, largestCity);

    }

    private void printData() {
        for (City city : listCities) {
            System.out.println(city.toString());
        }
        for (Flight flight : listFlights) {
            System.out.println(flight.toString());
        }
    }

    private void findRouteBetween(City start, City dest) {
        findRouteBetween(start, dest, null);
    }

    private void findRouteBetween(City start, City dest, String airLine) {
        LinkedList<Vertex> path = null;
        List<Edge> listEdges = new ArrayList<>();

        if (airLine == null) {
            listEdges.addAll(listFlights);
        } else {
            for (Flight flight : listFlights) {
                if (flight.getAirLine().equals(airLine)) {
                    listEdges.add((Edge) flight);
                }
            }
        }

        List<Vertex> listVertexes = new ArrayList<>(listCities);
        Graph graph = new Graph(listVertexes, listEdges);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(start);
        path = dijkstraAlgorithm.getPath(dest);


        if (path != null) {
            for (Vertex vertex : path) {
                System.out.println(vertex);
            }
        } else {
            System.out.println("No route");
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
                            Utils.readFlightXMLelements(reader, listFlights, listCities, listAirlines);
                        }
                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        mElementName = reader.getLocalName();
                        if (mElementName.equals(FLIGHT_ELEMENT_NAME)) {
                            Utils.readFlightXMLelements(reader, listFlights, listCities, listAirlines);
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
