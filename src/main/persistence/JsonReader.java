package persistence;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import model.City;
import model.Location;

/*
 *  Represents a reader that reads a map from JSON data stored in file
 */

public class JsonReader {

    // EFFETS: constructs reader to read data from source file
    public JsonReader(String src) {
        
    };


    // EFFECTS: reads Locations from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Location> readLocations() throws IOException {
        return null;
    }

    // EFFECTS: reads Location from file and returns it;
    // throws IOException if an error occurs reading data from file
    private Location readLocation() throws IOException {
        return null;
    }

    // EFFECTS: reads city from file and returns it;
    // throws IOException if an error occurs reading data from file
    private City readCity() throws IOException {
        return null;
    }

    // EFFECTS: reads Citys from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<City> readCities() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses location from JSON object and returns it
    private Location parseLocation(JSONObject jsonObject) {
        return null;
    }

    // EFFECTS: parses city from JSON object and returns it
    private City parseCity(JSONObject jsonObject) {
        return null;
    }

}
