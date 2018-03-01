package utils;

import models.City;
import models.Flight;

import javax.xml.stream.XMLStreamReader;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static void readFlightXMLelements(XMLStreamReader reader, List<Flight> listFlights){

        String startCityName;
        String destCityName;
        Duration duration;
        String airLine;
        int flightDistance;
        Flight flight;

        String[] hoursAndMinutes;

        startCityName = reader.getAttributeValue(0);
        destCityName = reader.getAttributeValue(1);
        hoursAndMinutes = reader.getAttributeValue(2).split(":");
        flightDistance = Integer.parseInt(reader.getAttributeValue(3));
        airLine = reader.getAttributeValue(4);

        // get the hours, minutes and seconds value and add it to the mDuration
        duration = Duration.ofHours(Integer.parseInt(hoursAndMinutes[0]));
        duration = duration.plusMinutes(Integer.parseInt(hoursAndMinutes[1]));

        flight = new Flight(startCityName, destCityName, duration, flightDistance, airLine);
        listFlights.add(flight);
    }

    public static void readCityXMLelements(XMLStreamReader reader, List<City> listFlights){
        String cityName;
        int population;
        City city;

        cityName = reader.getAttributeValue(0);
        population = Integer.parseInt(reader.getAttributeValue(1));

        city = new City(cityName, population);
        listFlights.add(city);
    }
}
