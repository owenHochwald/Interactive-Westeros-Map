package model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class TestMap {

    private ArrayList<City> cities;
    private ArrayList<Location> locations;
    private Map mapTest;

    @BeforeEach
    void setup() {
        cities.add(new City("King's Landing", 500000, "Baratheon", "Crownlands", true, false));
        cities.add(new City("Winterfell", 100000, "Stark", "The North", true, false));

        locations.add(new Location("Gods Eye", "The Riverlands", false));
        locations.add(new Location("The Wall", "The North", false));
        locations.add(new Location("Wolf's Wood", "The North", false));
        mapTest = new Map(locations, cities);
    }

    @Test
    void testConstuctor() {
        assertEquals(2, mapTest.getCities().size());
        assertEquals(3, mapTest.getLocations().size());
        assertEquals("King's Landing", mapTest.getCities().get(0));
        assertEquals("Wolf's Wood", mapTest.getLocations().get(2));
    }

    @Test
    void testSetLocations() {
        assertEquals(3, mapTest.getLocations().size());
        mapTest.setLocations(null);
        assertNull(mapTest.getLocations());
        locations = new ArrayList<>();
        locations.add(new Location("The Wall", "The North", false));
        mapTest.setLocations(locations);
        assertEquals(1, mapTest.getLocations().size());
    }

    @Test
    void testSetCities() {
        assertEquals(3, mapTest.getCities().size());
        mapTest.setCities(null);
        assertNull(mapTest.getCities());
        cities = new ArrayList<>();
        cities.add(new City("Winterfell", 100000, "Stark", "The North", true, false));
        mapTest.setCities(cities);
        assertEquals(1, mapTest.getCities().size());
    }

    @Test
    void testLoadMap() {
        mapTest.setCities(null);
        mapTest.setLocations(null);
        mapTest.loadMap("./data/testReaderGeneralMapState.json");
        assertEquals(2, mapTest.getCities().size());
        assertEquals(2, mapTest.getLocations().size());
    }

    @Test
    void testWriteMap() {
        mapTest.saveMap("./data/testWriterGeneralMapState.json");
        mapTest.loadMap("./data/testWriterGeneralMapState.json");
        assertEquals(2, mapTest.getCities().size());
        assertEquals(2, mapTest.getLocations().size());
    }
}
