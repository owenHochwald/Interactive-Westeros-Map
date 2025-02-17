package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Represents a Map with locations and cities. Provides methods on that data 
 * to interact and manage the map.
 */
public class Map {
    ArrayList<Location> locations;
    ArrayList<City> cities;

    // EFFECTS: constructs a new map with cities and locations
    public Map(ArrayList<Location> locations, ArrayList<City> cities) {
        this.locations = locations;
        this.cities = cities;
    }

    // MODIFIES: this
    // EFFECTS: loads the map state from file
    public void loadMap(String path) throws IOException {
        JsonReader reader = new JsonReader(path);
        cities = reader.readCities();
        locations = reader.readLocations();
    }

    // MODIFIES: json file at path
    // EFFECTS: saves the current map state to file
    public void saveMap(String path) throws FileNotFoundException {
        JsonWriter saver = new JsonWriter(path);
        saver.open();
        saver.write(locations, cities);
        saver.close();

    }

    // getters
    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}
