package controller;

import engine.DijkstraAlgorithm;
import interfaces.Edge;
import interfaces.Vertex;
import models.City;
import models.Flight;
import models.Graph;
import utils.Utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daria Kalashnikova on 3/3/2018.
 */

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
        printData();

        smallestCity = Utils.findSmallestCity(listCities);
        largestCity = Utils.findLargestCity(listCities);

        System.out.println("Smallest city: " + smallestCity);
        System.out.println("Largest city: ".concat(largestCity + "\n"));

        for (String currentAirLine : listAirlines) {
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

        System.out.println();
    }

    private void findRouteBetween(City start, City dest) {
        findRouteBetween(start, dest, null);
    }

    private void findRouteBetween(City start, City dest, String airLine) {
        LinkedList<Vertex> listOfCities = null;
        List<Edge> listEdges = new ArrayList<>();

        if (airLine == null) {
            listEdges.addAll(listFlights);
        } else {
            for (Flight flight : listFlights) {
                if (flight.getAirLine().equals(airLine)) {
                    listEdges.add(flight);
                }
            }
        }

        List<Vertex> listVertexes = new ArrayList<>(listCities);
        Graph graph = new Graph(listVertexes, listEdges);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(start);
        listOfCities = dijkstraAlgorithm.getPath(dest);


        Duration totalFlightTime = Duration.ofMinutes(0);
        if (listOfCities != null) {

            for (int i = 0; i < listOfCities.size() - 1; i++) {

                for (Edge flight : listEdges) {
                    if (flight.getSource() == listOfCities.get(i)
                            && flight.getDestination() == listOfCities.get(i + 1)) {

                        System.out.println(flight);
                        totalFlightTime = totalFlightTime.plus(((Flight) flight).getFlightDuration());

                        /*
                          adding the leftover minutes, except from the last flight
                          as described in Task 2
                         */
                        if (i < listOfCities.size() - 2) {
                            long leftoverMinutes = totalFlightTime.toMinutes() - totalFlightTime.toHours() * 60;
                            totalFlightTime = totalFlightTime.plusMinutes(60 - leftoverMinutes);
                        }

                        break;
                    }
                }
            }

            System.out.println("Total time: " + Flight.durationToString(totalFlightTime));

        } else {
            System.out.println("No route");
        }


    }

    private XMLStreamReader openXml() throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader("flights.xml");
        } catch (FileNotFoundException ex) {
            System.out.println("Error: The file is missing!");
        }

        return inputFactory.createXMLStreamReader(fileReader);
    }

    private void readXMLCities() {
        try {
            // Create a stream reader object
            XMLStreamReader reader = openXml();
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

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void readXMLFlights() {
        try {
            // Create a stream reader object
            XMLStreamReader reader = openXml();
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

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
