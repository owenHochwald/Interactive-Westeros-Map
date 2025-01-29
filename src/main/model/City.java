package model;

import java.util.ArrayList;

/*
 * Represents a city with a name, population (number of poeple  living there),
 * list of cities the city is allied with, the house the city belongs to, what region the city is located in,
 * wether or not the city has been visited by the user, and wether or not the city is a capital city.
 */
public class City {
    
    private String name;
    private int population;
    private String house;
    private String region;
    private ArrayList<City> alliances;
    private boolean isCapital;
    private boolean visited;


    // REQUIRES: name, region, house must not be null / empty,  population >= 10
    // MODIFIES: this, Progress
    // EFFECTS: creates a new unvisited city with given name, population, alliances, house, region,
    //          capital status, and no alliances. Increases total number of cities by 1.
    public City(String name, int population, String house, String region, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.house = house;
        this.region = region;
        this.alliances = new ArrayList<City>();
        this.isCapital = isCapital;
        this.visited = false;
        Progress.increaseTotalCities();

    }

    // MODIFIES: this
    // EFFECTS: increases population by 1,000
    public void increasePopulation() {
        this.population += 1000;
        
    }

    // MODIFES: this
    // EFFECTS: toggles wether a city is visited or not
    public void toggleVisited() {
        if (!visited) {
            Progress.increaseNumVisited();
        } else {
            Progress.decreaseNumVisited();
        }
        visited = !visited;
    }


    // MODIFIES: this
    // EFFECTS: toggles wether a city is a capital city or not
    public void toggleCapital() {
        isCapital = !isCapital;
    }

    // REQUIRES: a well-formed city, city is not already an ally
    // MODIFES: this, city
    // EFFECTS: creates an alliance between two cities
    public void addAlliance(City city) {
        alliances.add(city);
        if (!city.getAlliances().contains(this)) {
            city.addAlliance(this);
        }

    }

    // REQUIRES: a well-formed city, city is in alliances
    // MODIFES: this, city
    // EFFECTS: removes an alliance between two cities
    public void removeAlliance(City city) {
        alliances.remove(city);
        if (city.getAlliances().contains(this)) {
            city.removeAlliance(this);
        }

    }
    
    // setters
    public void setHouse(String house) {
        this.house = house;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    // getters

    public String getName() {
        return name;
    }
    
    public int getPopulation() {
        return population;
    }

    public ArrayList<City> getAlliances() {
        return alliances;
    }

    public String getRegion() {
        return region;
    }

    public String getHouse() {
        return house;
    }

    public boolean getIsCapital() {
        return isCapital;
    }

    public boolean getVisited() {
        return visited;
    }

    
}
