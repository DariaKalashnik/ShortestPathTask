package models;

import java.time.Duration;
import java.time.LocalTime;

public class Flight implements Edge {

    private String id;
    private String startCityName;
    private String destCityName;
    private Duration flightDuration;
    private int flightDistance;
    private String airLine;

    public Flight () {}

    public Flight(String id, String startCityName, String destCityName, Duration flightDuration, int flightDistance, String airLine) {
        this.id = id;
        this.startCityName = startCityName;
        this.destCityName = destCityName;
        this.flightDuration = flightDuration;
        this.flightDistance = flightDistance;
        this.airLine = airLine;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getDestCityName() {
        return destCityName;
    }

    public void setDestCityName(String destCityName) {
        this.destCityName = destCityName;
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
        return null;
    }

    @Override
    public Vertex getSource() {
        return null;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "startCityName='" + startCityName + '\'' +
                ", destCityName='" + destCityName + '\'' +
                ", flightDuration=" + flightDuration +
                ", flightDistance=" + flightDistance +
                ", airLine='" + airLine + '\'' +
                '}';
    }
}
