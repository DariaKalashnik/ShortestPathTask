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

    // Empty constructor
    public Flight() {
    }

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
        flightTime += (duration.toMinutes() - duration.toHours() * 60) + " minutes ";
        return flightTime;
    }

    public String getAirLine() {
        return airLine;
    }

    public Duration getFlightDuration() {
        return flightDuration;
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
    public String toString() {
        return startCity.getName() + " -> " + destCity.getName() +
                ", duration: " + durationToString(flightDuration) +
                ", flightDistance: " + flightDistance + "kms " +
                ", airLine: " + airLine;
    }
}
