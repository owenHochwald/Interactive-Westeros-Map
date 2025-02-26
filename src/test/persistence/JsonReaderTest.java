package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.City;
import model.Location;
import model.Map;

public class JsonReaderTest extends JsonTest {

    private ArrayList<Location> locations;
    private ArrayList<City> cities;
    private Map map;

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
    public void testMapReaderWithNoFilePath() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            map = reader.readMap();
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
            ArrayList<String> a1 = new ArrayList<>();
            ArrayList<String> a2 = new ArrayList<>();
            a1.add("Kings Landing");
            a2.add("Winterfell");
            checkCity(cities.get(0), "Winterfell", 15000, "Stark", "The North", true,
                    false, true, a1);
            checkCity(cities.get(1), "Kings Landing", 1000000, "Lannister", "Crownlands", true,
                    true, false, a2);
            checkLocation(locations.get(0), "Kings Road", "Crownlands", true, true);
            checkLocation(locations.get(1), "The Gods Eye", "The Riverlands", false, false);
            assertEquals("Winterfell", cities.get(1).getAlliances().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoomWithMap() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMapState.json");
        try {
            map = reader.readMap();
            assertEquals(2, map.getCities().size());
            assertEquals(2, map.getLocations().size());
            ArrayList<String> a1 = new ArrayList<>();
            ArrayList<String> a2 = new ArrayList<>();
            a1.add("Kings Landing");
            a2.add("Winterfell");
            checkCity(map.getCities().get(0), "Winterfell", 15000, "Stark", "The North", true,
                    false, true, a1);
            checkCity(map.getCities().get(1), "Kings Landing", 1000000, "Lannister", "Crownlands", true,
                    true, false, a2);
            checkLocation(map.getLocations().get(0), "Kings Road", "Crownlands", true, true);
            checkLocation(map.getLocations().get(1), "The Gods Eye", "The Riverlands", false, false);
            assertEquals("Winterfell", map.getCities().get(1).getAlliances().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
