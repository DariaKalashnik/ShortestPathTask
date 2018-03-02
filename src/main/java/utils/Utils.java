package utils;

import models.City;
import models.Flight;

import javax.xml.stream.XMLStreamReader;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static void readFlightXMLelements(XMLStreamReader reader, List<Flight> listFlights){

        String id;
        String startCityName;
        String destCityName;
        Duration duration;
        String airLine;
        int flightDistance;
        Flight flight;

        String[] hoursAndMinutes;

        id = reader.getAttributeValue(0);
        startCityName = reader.getAttributeValue(1);
        destCityName = reader.getAttributeValue(2);
        hoursAndMinutes = reader.getAttributeValue(3).split(":");
        flightDistance = Integer.parseInt(reader.getAttributeValue(4));
        airLine = reader.getAttributeValue(5);

        // get the hours, minutes and seconds value and add it to the mDuration
        duration = Duration.ofHours(Integer.parseInt(hoursAndMinutes[0]));
        duration = duration.plusMinutes(Integer.parseInt(hoursAndMinutes[1]));

        flight = new Flight(id, startCityName, destCityName, duration, flightDistance, airLine);
        listFlights.add(flight);
    }

    public static void readCityXMLelements(XMLStreamReader reader, List<City> listFlights){
        String id;
        String cityName;
        int population;
        City city;

        id = reader.getAttributeValue(0);
        cityName = reader.getAttributeValue(1);
        population = Integer.parseInt(reader.getAttributeValue(2));

        city = new City(id, cityName, population);
        listFlights.add(city);
    }
}
