package models;

import interfaces.Edge;
import interfaces.Vertex;

import java.time.Duration;

public class Flight implements Edge {

    private String id;
    private City startCity;
    private City destCity;
    private Duration flightDuration;
    private int flightDistance;
    private String airLine;

    public Flight () {}

    public Flight(String id, City startCity, City destCity, Duration flightDuration, int flightDistance, String airLine) {
        this.id = id;
        this.startCity = startCity;
        this.destCity = destCity;
        this.flightDuration = flightDuration;
        this.flightDistance = flightDistance;
        this.airLine = airLine;
    }

    public static String durationToString(Duration duration) {
        String flightTime = duration.toHours() > 0 ? duration.toHours() + " hours " : "";
        flightTime += (duration.toMinutes() - duration.toHours()*60)  + " minutes ";
        return flightTime;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getDestCity() {
        return destCity;
    }

    public void setDestCity(City destCity) {
        this.destCity = destCity;
    }

    public Duration getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Duration flightDuration) {
        this.flightDuration = flightDuration;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(int flightDistance) {
        this.flightDistance = flightDistance;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Vertex getDestination() {
        return destCity;
    }

    @Override
    public Vertex getSource() {
        return startCity;
    }

    @Override
    public int getWeight() {
        return (int) flightDuration.toMinutes();
    }

    @Override
    public String toString() {return startCity.getName() + " -> " + destCity.getName() +
                ", duration: " +
                durationToString(flightDuration) +
                ", flightDistance: " + flightDistance + "kms " +
                ", airLine: " + airLine;
    }
}
