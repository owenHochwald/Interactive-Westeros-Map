package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.City;
import model.Location;
import model.Map;

/*
 *  Represents a reader that reads a map from JSON data stored in file
 */

public class JsonReader {
    private String src;


    // EFFECTS: constructs reader to read data from source file
    public JsonReader(String src) {
        this.src = src;
    }

    // EFFECTS: reads a map from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Map readMap() throws IOException {
        return null;
    }

    // EFFECTS: reads Locations from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Location> readLocations() throws IOException {
        String jsonData = readFile(src);
        JSONObject jsonObject = new JSONObject(jsonData);
        ArrayList<Location> locations = new ArrayList<>();

        JSONArray locationsArray = jsonObject.getJSONArray("locations");
        for (int i = 0; i < locationsArray.length(); i++) {
            locations.add(parseLocation(locationsArray.getJSONObject(i)));
        }
        return locations;
    }

    // EFFECTS: reads Citys from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<City> readCities() throws IOException {
        String jsonData = readFile(src);
        JSONObject jsonObject = new JSONObject(jsonData);
        ArrayList<City> cities = new ArrayList<>();

        JSONArray citiesArray = jsonObject.getJSONArray("cities");
        for (int i = 0; i < citiesArray.length(); i++) {
            cities.add(parseCity(citiesArray.getJSONObject(i)));
        }
        return cities;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses location from JSON object and returns it
    private Location parseLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String region = jsonObject.getString("region");
        boolean customMade = jsonObject.getBoolean("customMade");
        boolean visited = jsonObject.getBoolean("visited");
        Location location = new Location(name, region, customMade);
        if (visited) {
            location.toggleVisited();
        }
        return location;
    }

    // EFFECTS: parses city from JSON object and returns it
    private City parseCity(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int population = jsonObject.getInt("population");
        String house = jsonObject.getString("house");
        String region = jsonObject.getString("region");
        boolean capital = jsonObject.getBoolean("isCapital");
        boolean customMade = jsonObject.getBoolean("customMade");
        boolean visited = jsonObject.getBoolean("visited");
        JSONArray alliancesArray = jsonObject.getJSONArray("alliances");
        ArrayList<String> alliances = new ArrayList<>();
        for (int i = 0; i < alliancesArray.length(); i++) {
            String allianceName = alliancesArray.getString(i);
            alliances.add(allianceName);
        }
        City city = new City(name, population, house, region, capital, customMade);
        if (visited) {
            city.toggleVisited();
        }
        for (String allianceName : alliances) {
            city.addAlliance(allianceName);
        }
        return city;
    }

}
