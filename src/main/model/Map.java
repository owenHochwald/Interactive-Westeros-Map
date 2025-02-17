package model;

import java.util.ArrayList;

/*
 * Represents a Map with locations and cities. Provides methods on that data 
 * to interact and manage the map.
 */
public class Map {

    // EFFECTS: constructs a new map with cities and locations
    public Map(ArrayList<Location> locations, ArrayList<City> cities) {

    }


    // MODIFIES: this
    // EFFECTS: loads the map state from file
    public void loadMap(String path) {

    }

    // MODIFIES: mapState
    // EFFECTS: saves the current map state to file
    public void saveMap(String path) {

    }

    // getters
    public ArrayList<Location> getLocations() {
        return null;
    }

    public ArrayList<City> getCities() {
        return null;
    }

    public void setLocations(ArrayList<Location> locations) {

    }

    public void setCities(ArrayList<City> cities) {

    }

}
