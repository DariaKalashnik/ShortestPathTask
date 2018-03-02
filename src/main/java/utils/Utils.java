package utils;

import models.City;
import models.Flight;

import javax.xml.stream.XMLStreamReader;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static void readFlightXMLelements(XMLStreamReader reader, List<Flight> listFlights, List<City> listCities, List<String> listAirlines){

        String id;
        String startCityName;
        String destCityName;
        City startCity = null;
        City destCity = null;;
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

        for (City city: listCities) {
            if(city.getName().equals(startCityName)) {
                startCity = city;
            } else if (city.getName().equals(destCityName)) {
                destCity = city;
            }
        }

        if(!listAirlines.contains(airLine)) {
            listAirlines.add(airLine);
        }

        flight = new Flight(id, startCity, destCity, duration, flightDistance, airLine);
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

    public static City findSmallestCity(List<City> listCities){
        int smallestPopulation = Integer.MAX_VALUE;
        for (City city: listCities) {
            if (city.getCityPopulation() < smallestPopulation){
                smallestPopulation = city.getCityPopulation();
            }
        }

        for (City city: listCities) {
            if (city.getCityPopulation() == smallestPopulation){
                return city;
            }
        }

        return null;
    }

    public static City findLargestCity(List<City> listCities){
        // alternate way
        if (listCities.size() > 0){
            City largestCity = new City("dummyCity", "dummyCity", Integer.MIN_VALUE);
            for (City city: listCities) {
                if (city.getCityPopulation() > largestCity.getCityPopulation()){
                    largestCity = city;
                }
            }
            return largestCity;
        }

        return null;
    }
}
