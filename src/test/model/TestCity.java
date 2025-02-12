package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCity {
    City city;

    @BeforeEach
    void runBefore() {
        Progress.setTotalNumCities(0);
        Progress.setNumCitiesVisited(0);
        Progress.setTotalNumEntries(0);
        Progress.setTotalNumVisited(0);
        city = new City("Winterfell", 15000, "Stark", "The North", true, false);

    }

    @Test
    void cityConstructorTest() {
        City c2 = new City("Kings Landing", 1000000, "Lannister", "Crownlands", true, true);
        assertEquals("Winterfell", city.getName());
        assertEquals(15000, city.getPopulation());
        assertEquals("Stark", city.getHouse());
        assertEquals("The North", city.getRegion());
        assertTrue(city.getIsCapital());
        assertTrue(city.getAlliances().isEmpty());
        assertFalse(city.getVisited());
        assertEquals(2, Progress.getTotalNumCities());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(2, Progress.getTotalNumEntries());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
        assertTrue(c2.customMade());
        assertFalse(city.customMade());
    }

    @Test
    void increasePopulationOnceTest() {
        city.increasePopulation();
        assertEquals(16000, city.getPopulation());
    }

    @Test
    void increasePopulationTwiceTest() {
        city.increasePopulation();
        city.increasePopulation();
        assertEquals(17000, city.getPopulation());
    }

    @Test
    void setHouseTest() {
        city.setHouse("Bolton");
        assertEquals("Bolton", city.getHouse());
    }

    @Test
    void toggleVisitedWhenFalseTest() {
        assertFalse(city.getVisited());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
        city.toggleVisited();
        assertEquals(1, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumVisitedEntries());
        assertTrue(city.getVisited());
    }

    @Test
    void toggleVisitedWhenTrueTest() {
        city.toggleVisited();
        assertEquals(1, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumVisitedEntries());
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
    }

    @Test
    void setPopulationTest() {
        city.setPopulation(10000000);
        assertEquals(10000000, city.getPopulation());
    }


    @Test
    void toggleVisitedTrueTwiceTest() {
        city.toggleVisited();
        assertEquals(1, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumVisitedEntries());
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
        city.toggleVisited();
        assertTrue(city.getVisited());
        assertEquals(1, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumVisitedEntries());
    }

    @Test
    void toggleCapitalWhenTrueTest() {
        assertTrue(city.getIsCapital());
        city.toggleCapital();
        assertFalse(city.getIsCapital());
    }

    @Test
    void toggleCapitalWhenFalseTest() {
        city.toggleCapital();
        assertFalse(city.getIsCapital());
        city.toggleCapital();
        assertTrue(city.getIsCapital());
    }


    @Test
    void toggleCapitalFalsewiceTest() {
        city.toggleCapital();
        assertFalse(city.getIsCapital());
        city.toggleCapital();
        assertTrue(city.getIsCapital());
        city.toggleCapital();
        assertFalse(city.getIsCapital());
    }

    @Test
    void addCityStringTest() {
        assertEquals(1, Progress.getTotalNumCities());
        city.addAlliance("test1");
        assertEquals("test1", city.getAlliances().get(0));
        assertEquals(1, city.getAlliances().size());
        city.addAlliance("test2");
        assertEquals("test2", city.getAlliances().get(1));
        assertEquals(2, city.getAlliances().size());
        city.removeAlliance("test1");
        assertEquals(1, city.getAlliances().size());
        city.removeAlliance("test2");
        assertEquals(0, city.getAlliances().size());

    }
    @Test
    void getRegionTest() {
        assertEquals("The North", city.getRegion());
    }
}
