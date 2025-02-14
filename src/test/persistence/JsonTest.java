package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import model.City;
import model.Location;

public class JsonTest {

    protected void checkLocation(Location location, String name, String region, boolean customMade, boolean visited) {
        assertEquals(name, location.getName());
        assertEquals(region, location.getRegion());
        assertEquals(customMade, location.customMade());
        assertEquals(visited, location.getVisited());
    }

    protected void checkCity(City city, String name, int population, String house, String region, boolean isCapital,
            boolean customMade, boolean visited, ArrayList<String> alliances) {
        assertEquals(name, city.getName());
        assertEquals(region, city.getRegion());
        assertEquals(customMade, city.customMade());
        assertEquals(population, city.getPopulation());
        assertEquals(house, city.getHouse());
        assertEquals(isCapital, city.getIsCapital());
        assertEquals(visited, city.getVisited());
        assertEquals(alliances, city.getAlliances());

    }
}