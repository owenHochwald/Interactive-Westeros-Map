package model;

import java.util.ArrayList;

/*
 * Represents a city with a name, population (number of poeple  living there),
 * list of cities the city is allied with, the house the city belongs to, what region the city is located in,
 * wether or not the city has been visited by the user, wether or not the city is a capital city, and wether or
 * not the city is custom.
 */
public class City extends Location {

    private int population;
    private String house;
    private ArrayList<String> alliances;
    private boolean isCapital;

    // REQUIRES: name, region, house must not be null / empty, population >= 10
    // MODIFIES: this, Progress
    // EFFECTS: creates a new unvisited city with given name, population, alliances,
    // house, region,
    // capital status, and no alliances. Increases total number of cities by 1, set
    // customMade to wether city is custom made or not
    public City(String name, int population, String house, String region, boolean isCapital, boolean customMade) {
        super(name, region, customMade);
        this.population = population;
        this.house = house;
        this.isCapital = isCapital;
        this.alliances = new ArrayList<>();
        Progress.increaseTotalCities();
        EventLog.getInstance().logEvent(new Event("Created city " + name + " to the map."));


    }

    // MODIFIES: this
    // EFFECTS: increases population by 1,000
    public void increasePopulation() {
        this.population += 1000;
        EventLog.getInstance().logEvent(new Event("Population for " + name + " increased by 1000."));
    }

    // MODIFES: this, Progress
    // EFFECTS: toggles wether a location is visited or not
    @Override
    public void toggleVisited() {
        if (!visited) {
            Progress.increaseNumVisitedEntries();
            Progress.increaseNumCitiesVisited();
        } else {
            Progress.decreasesNumVisitedEntries();
            Progress.decreaseNumCitiesVisited();
        }
        visited = !visited;
        EventLog.getInstance().logEvent(new Event("Toggled visit status for " + name));
    }

    // MODIFIES: this
    // EFFECTS: toggles wether a city is a capital city or not
    public void toggleCapital() {
        isCapital = !isCapital;
    }

    // REQUIRES: city cannot be empty / null, city is not already an ally
    // MODIFES: this
    // EFFECTS: adds a city to this alliances
    public void addAlliance(String city) {
        if (!alliances.contains(city)) {
            alliances.add(city);
        }

    }

    // REQUIRES: city cannot be empty / null, city is in ally
    // MODIFES: this, city
    // EFFECTS: removes an alliance between two cities
    public void removeAlliance(String city) {
        if (alliances.contains(city)) {
            alliances.remove(city);
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

    public int getPopulation() {
        return population;
    }

    public ArrayList<String> getAlliances() {
        return alliances;
    }

    public String getHouse() {
        return house;
    }

    public boolean getIsCapital() {
        return isCapital;
    }

}
