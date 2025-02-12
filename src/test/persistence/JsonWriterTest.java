package persistence;

import org.junit.jupiter.api.Test;

import model.City;
import model.Location;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            @SuppressWarnings("unused")
            Location location = new Location("Owen", "Owen", false);
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            City c1 = new City("Winterfell", 15000, "Stark", "The North", true, false);
            c1.toggleVisited();
            City c2 = new City("Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
            c2.toggleVisited();
            c1.addAlliance(c2);
            Location l1 = new Location("Kings Road", "Crownlands", true);
            Location l2 = new Location("The Gods Eye", "The Riverlands", false);
            l1.toggleVisited();

            ArrayList<String> alliances = new ArrayList<>();
            alliances.add("Kings Landing");

            ArrayList<Location> locations = new ArrayList<>();
            locations.add(l1);
            locations.add(l2);
            ArrayList<City> cities = new ArrayList<>();
            cities.add(c1);
            cities.add(c2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(locations, cities);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            ArrayList<Location> readLocations = reader.readLocations();
            ArrayList<City> readCities = reader.readCities();
            assertEquals(2, readLocations.size());
            assertEquals(2, readCities.size());
            assertTrue(readCities.get(0).getVisited());
            assertTrue(readLocations.get(0).getVisited());
            checkCity(readCities.get(0), "Winterfell", 15000, "Stark", "The North", true, false);
            checkCity(readCities.get(1), "Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
            checkLocation(readLocations.get(0), "Kings Road", "Crownlands", true);
            checkLocation(readLocations.get(1), "The Gods Eye", "The Riverlands", false);
            // TODO: add alliance checking 

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}