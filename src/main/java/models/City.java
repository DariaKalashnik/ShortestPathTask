package models;

import interfaces.Vertex;

import java.util.Objects;

/**
 * Created by Daria Kalashnikova on 3/3/2018.
 */

public class City implements Vertex {

    private String id;
    private String cityName;
    private int cityPopulation;

    public City(String id, String cityName, int cityPopulation) {
        this.id = id;
        this.cityName = cityName;
        this.cityPopulation = cityPopulation;
    }

    @Override
    public String getId() {
        return id;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    @Override
    public String getName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "City name = " + cityName +
                ", Population = " + cityPopulation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
