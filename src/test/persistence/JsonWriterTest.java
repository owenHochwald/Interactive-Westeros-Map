package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.City;
import model.Location;
import model.Map;
import model.Progress;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    // NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter
    // is to
    // write data to a file and then use the reader to read it back in and check
    // that we
    // read in a copy of what was written out.

    @BeforeEach
    void clearProgress() {
        Progress.resetCityProgress();
        Progress.resetEntryProgress();
        Progress.resetTotalCities();
        Progress.resetTotalEntry();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Location location = new Location("Owen", "Owen", false);
            assertEquals("Owen", location.getName());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyState() {
        try {
            Location location = new Location("Owen", "Owen", false);
            City city = new City("test", 10, "test", "test", false, false);
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(location);
            ArrayList<City> cities = new ArrayList<>();
            cities.add(city);
            JsonWriter writer = new JsonWriter("./data/testMapEmptyState.json");
            writer.open();
            writer.write(locations, cities);
            writer.close();

            JsonReader reader = new JsonReader("./data/testMapEmptyState.json");
            location = reader.readLocations().get(0);
            city = reader.readCities().get(0);
            assertEquals("Owen", location.getName());
            assertEquals("test", city.getName());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @SuppressWarnings("methodlength")
    void testWriterGeneralMap() {
        try {
            City c1 = new City("Winterfell", 15000, "Stark", "The North", true, false);
            City c2 = new City("Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
            Location l1 = new Location("Kings Road", "Crownlands", true);
            Location l2 = new Location("The Gods Eye", "The Riverlands", false);
            l1.toggleVisited();
            ArrayList<String> a1 = new ArrayList<>();
            ArrayList<String> a2 = new ArrayList<>();
            writerGeneralMapInit(c1, c2, a1, a2);
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(l1);
            locations.add(l2);
            ArrayList<City> cities = new ArrayList<>();
            cities.add(c1);
            cities.add(c2);
            writeToFile(locations, cities);
            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            ArrayList<Location> readLocations = reader.readLocations();
            ArrayList<City> readCities = reader.readCities();
            writerGeneralMapChecker(readLocations, readCities);
            checkCity(readCities.get(0), "Winterfell", 15000, "Stark", "The North", true,
                    false, true, a1);
            checkCity(readCities.get(1), "Kings Landing", 1000000, "Lannister", "Crownlands",
                    true, true, true, a2);

        } catch (IOException e) {
            fail();
        }
    }

    public void writerGeneralMapChecker(ArrayList<Location> readLocations, ArrayList<City> readCities) {
        assertEquals(2, readLocations.size());
        assertEquals(2, readCities.size());
        assertTrue(readCities.get(0).getVisited());
        assertTrue(readLocations.get(0).getVisited());
        checkLocation(readLocations.get(0), "Kings Road", "Crownlands", true, true);
        checkLocation(readLocations.get(1), "The Gods Eye", "The Riverlands", false, false);
        assertEquals(4, Progress.getTotalNumCities());
        assertEquals(6, Progress.getTotalNumVisitedEntries());
        assertEquals(8, Progress.getTotalNumEntries());
    }

    public void writerGeneralMapInit(City c1, City c2, ArrayList<String> a1, ArrayList<String> a2) {
        c1.toggleVisited();
        c2.toggleVisited();
        c1.addAlliance(c2.getName());
        c2.addAlliance(c1.getName());
        a1.add("Kings Landing");
        a2.add("Winterfell");
    }

    public void writeToFile(ArrayList<Location> locations, ArrayList<City> cities) throws IOException {
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
        writer.open();
        writer.write(locations, cities);
        writer.close();
    }

    @Test
    public void testWriteMapToFile() {
        try {
            Map map = initMap();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(map);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            Map readMap = reader.readMap();
            assertEquals(2, readMap.getCities().size());
            assertEquals(3, readMap.getLocations().size());
        } catch (IOException e) {
            fail();
        }
    }

    // EFFECTS: returns a init map for test use
    public Map initMap() {
        City c1 = new City("Winterfell", 15000, "Stark", "The North", true, false);
        City c2 = new City("Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
        Location l1 = new Location("Kings Road", "Crownlands", true);
        Location l2 = new Location("The Gods Eye", "The Riverlands", false);
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);
        locations.add(l1);
        Map map = new Map(locations, cities);
        return map;
    }
}