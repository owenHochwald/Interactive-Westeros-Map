package persistence;

import model.Location;
import model.City;



import java.io.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB =  4;
    private PrintWriter writer;
    private String destination;


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of locations and cities to file
    public void write(ArrayList<Location> locations, ArrayList<City> cities) {
        JSONObject json = new JSONObject();
        json.put("locations", convertLocationsToJson(locations));
        json.put("cities", convertCitiesToJson(cities));
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: converts list of locations to JSON array
    private JSONArray convertLocationsToJson(ArrayList<Location> locations) {
        JSONArray locationArray = new JSONArray();
        for (Location location : locations) {
            JSONObject locationJson = new JSONObject();
            locationJson.put("name", location.getName());
            locationJson.put("region", location.getRegion());
            locationJson.put("customMade", location.customMade());
            locationJson.put("visited", location.getVisited());
            locationArray.put(locationJson);
        }
        return locationArray;
    }

    // EFFECTS: converts list of cities to JSON array
    private JSONArray convertCitiesToJson(ArrayList<City> cities) {
        JSONArray cityArray = new JSONArray();
        for (City city : cities) {
            JSONObject cityJson = new JSONObject();
            cityJson.put("name", city.getName());
            cityJson.put("population", city.getPopulation());
            cityJson.put("house", city.getHouse());
            cityJson.put("region", city.getRegion());
            cityJson.put("visited", city.getVisited());
            cityJson.put("isCapital", city.getIsCapital());
            cityJson.put("customMade", city.customMade());

            JSONArray allianceArray = new JSONArray();
            for (String allianceCity : city.getAlliances()) {
                allianceArray.put(allianceCity);  
            }
            cityJson.put("alliances", allianceArray);

            cityArray.put(cityJson);
        }
        return cityArray;
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
