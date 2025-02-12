package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.City;
import model.Location;

public class JsonReaderTest extends JsonTest {

    private ArrayList<Location> locations;
    private ArrayList<City> cities;

    @Test
    public void testReaderNonExistentFileForLocation() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            locations = reader.readLocations();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderNonExistentFileForCity() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            cities = reader.readCities();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyMapStateLocations() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMap.json");
        try {
            locations = reader.readLocations();
            cities = reader.readCities();
            assertEquals(0, locations.size());
            assertEquals(0, cities.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    
    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMapState.json");
        try {
            locations = reader.readLocations();
            cities = reader.readCities();
            assertEquals(2, locations.size());
            assertEquals(2, cities.size());
            checkCity(cities.get(0), "Winterfell", 15000, "Stark", "The North", true, false);
            checkCity(cities.get(1), "Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
            checkLocation(locations.get(0), "Kings Road", "Crownlands", true);
            checkLocation(locations.get(1), "The Gods Eye", "The Riverlands", false);
            // TODO: add case for when city has alliances
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    
}

