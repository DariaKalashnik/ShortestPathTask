package models;

import java.util.Objects;

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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "City name = " + cityName
                + ", Population = " + cityPopulation;
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
