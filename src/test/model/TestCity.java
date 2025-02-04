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
        Progress.setNumVisited(0);
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
        assertEquals(1, Progress.getTotalNumCities());
        assertEquals(0, Progress.getNumVisited());
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
        assertEquals(0, Progress.getNumVisited());
        city.toggleVisited();
        assertEquals(1, Progress.getNumVisited());
        assertTrue(city.getVisited());
    }

    @Test
    void toggleVisitedWhenTrueTest() {
        city.toggleVisited();
        assertEquals(1, Progress.getNumVisited());
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
        assertEquals(0, Progress.getNumVisited());
    }

    @Test
    void setPopulationTest() {
        city.setPopulation(10000000);
        assertEquals(10000000, city.getPopulation());
    }


    @Test
    void toggleVisitedTrueTwiceTest() {
        city.toggleVisited();
        assertEquals(1, Progress.getNumVisited());
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
        assertEquals(0, Progress.getNumVisited());
        city.toggleVisited();
        assertTrue(city.getVisited());
        assertEquals(1, Progress.getNumVisited());
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
    void addCityOnceTest() {
        City c1 = new City("test", 10, "none", "Iron islands", false, false);
        assertEquals(2, Progress.getTotalNumCities());
        city.addAlliance(c1);
        assertEquals(1, c1.getAlliances().size());
        assertEquals(city, c1.getAlliances().get(0));
        assertEquals(1, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
    }

    @Test
    void addCityTwiceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false, true);
        City c2 = new City("test 2", 100, "none", "Crownlands", true, true);
        assertEquals(3, Progress.getTotalNumCities());
        city.addAlliance(c1);
        city.addAlliance(c2);
        assertEquals(1, c1.getAlliances().size());
        assertEquals(city, c1.getAlliances().get(0));

        assertEquals(1, c2.getAlliances().size());
        assertEquals(city, c2.getAlliances().get(0));

        assertEquals(2, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
        assertEquals(c2, city.getAlliances().get(1));
    }

    @Test
    void removeCityOnceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false, false);
        City c2 = new City("test 2", 100, "none", "Crownlands", true, false);
        assertEquals(3, Progress.getTotalNumCities());
        city.addAlliance(c1);
        city.addAlliance(c2);
        assertEquals(1, c1.getAlliances().size());
        assertEquals(city, c1.getAlliances().get(0));

        assertEquals(1, c2.getAlliances().size());
        assertEquals(city, c2.getAlliances().get(0));

        assertEquals(2, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
        assertEquals(c2, city.getAlliances().get(1));

        city.removeAlliance(c1);
        assertEquals(0, c1.getAlliances().size());
        assertEquals(1, city.getAlliances().size());
    }


    @Test
    void removeCityTwiceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false, true);
        City c2 = new City("test 2", 100, "none", "Crownlands", true, false);
        assertEquals(3, Progress.getTotalNumCities());
        city.addAlliance(c1);
        city.addAlliance(c2);
        assertEquals(1, c1.getAlliances().size());
        assertEquals(city, c1.getAlliances().get(0));

        assertEquals(1, c2.getAlliances().size());
        assertEquals(city, c2.getAlliances().get(0));

        assertEquals(2, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
        assertEquals(c2, city.getAlliances().get(1));

        
        city.removeAlliance(c1);
        city.removeAlliance(c2);
        assertEquals(0, c1.getAlliances().size());
        assertEquals(0, c2.getAlliances().size());
        assertEquals(0, city.getAlliances().size());
    }

    @Test
    void addCityTwiceWithConnectedAllianceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false, true);
        City c2 = new City("test 2", 100, "none", "Crownlands", true, true);
        c1.addAlliance(c2);
        city.addAlliance(c1);
        city.addAlliance(c2);
        assertEquals(2, c1.getAlliances().size());
        assertEquals(c2, c1.getAlliances().get(0));

        assertEquals(2, c2.getAlliances().size());
        assertEquals(c1, c2.getAlliances().get(0));

        assertEquals(2, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
        assertEquals(c2, city.getAlliances().get(1));

        assertEquals(city, c1.getAlliances().get(1));
    }

    @Test
    void getRegionTest() {
        assertEquals("The North", city.getRegion());
    }
}
