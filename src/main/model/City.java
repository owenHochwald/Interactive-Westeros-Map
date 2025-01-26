package model;

import java.util.ArrayList;

/*
 * Represents a city with a name, population (number of poeple  living there),
 * list of cities the city is allied with, the lord of the city, what region the city is located in,
 * wether or not the city has been visited by the user, and wether or not the city is a capital city.
 */
public class City {
    
    private String name;
    private int population;
    private String lord;
    private String region;
    private ArrayList<City> alliances;
    private boolean isCapital;
    private boolean visited;


    // REQUIRES: non-empty name, population >= 10, non-empty lord, well-formed region
    // EFFECTS: creates a new unvisited city with given name, population, alliances, lord, region,
    //          capital status, and no alliances.
    public City(String name, int population, String lord, String region, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.lord = lord;
        this.region = region;
        this.alliances = new ArrayList<City>();
        this.isCapital = isCapital;
        this.visited = false;

    }

    // MODIFIES: this
    // EFFECTS: increases population by 1,000
    public void increasePopulation() {
        this.population += 1000;
        
    }

    // REQUIRES: a well-formed string
    // MODIFIES: this
    // EFFECTS: changes the lord of a city
    public void changeLord(String lord) {
        this.lord = lord;
    }

    // MODIFES: this
    // EFFECTS: toggles wether a city is visited or not
    public void toggleVisited() {
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

    public String getLord() {
        return lord;
    }

    public String getRegion() {
        return region;
    }

    public boolean getIsCapital() {
        return isCapital;
    }

    public boolean getVisited() {
        return visited;
    }

    
}
