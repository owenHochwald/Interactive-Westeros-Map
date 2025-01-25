package model;

import java.util.ArrayList;

/*
 * Represents a city with a name, population (number of poeple  living there),
 * list of cities the city is allied with, the lord of the city, what region the city is located in,
 * wether or not the city has been visited by the user, and wether or not the city is a capital city.
 */
public class City {

    // REQUIRES: non-empty name, population >= 10, non-empty lord, well-formed region
    // EFFECTS: creates a new unvisited city with given name, population, alliances, lord, region, capital status, and no alliances.
    public City(String name, int population, String lord, String region, boolean isCapital) {

    }

    // MODIFIES: this
    // EFFECTS: increases population by 1,000
    public void increasePopulation() {
        
    }

    // REQUIRES: a well-formed string
    // MODIFIES: this
    // EFFECTS: changes the lord of a city
    public void changeLord(String lord) {

    }

    // MODIFES: this
    // EFFECTS: toggles wether a city is visited or not
    public void toggleVisited() {

    }


    // MODIFIES: this
    // EFFECTS: toggles wether a city is a capital city or not
    public void toggleCapital() {

    }

    // REQUIRES: a well-formed city
    // MODIFES: this, city
    // EFFECTS: creates an alliance between two cities
    public void addAlliance(City city) {

    }

    // getters

    public String getName() {
        return "";
    }
    
    public int getPopulation() {
        return 10;
    }

    public ArrayList<City> getAlliances(){
        return new ArrayList<City>();
    }

    public String getLord(){
        return "";
    }

    public String getRegion() {
        return "";
    }

    public boolean getIsCapital() {
        return false;
    }

    public boolean getVisited() {
        return false;
    }

    
}
