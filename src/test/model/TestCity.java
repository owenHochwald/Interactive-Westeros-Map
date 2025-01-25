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
        city = new City("Winterfell", 15000, "Eddard Stark", "The North", true);

    }

    @Test
    void cityConstructorTest() {
        assertEquals("Winterfell", city.getName());
        assertEquals(15000, city.getPopulation());
        assertEquals("Eddard Stark", city.getLord());
        assertEquals("The North", city.getRegion());
        assertTrue(city.getIsCapital());
        assertTrue(city.getAlliances().isEmpty());
        assertFalse(city.getVisited());
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
    void changeLordTest() {
        city.changeLord("Ramsay Bolton");
        assertEquals("Ramsay Bolton", city.getLord());
    }

    @Test
    void toggleVisitedWhenFalseTest() {
        assertFalse(city.getVisited());
        city.toggleVisited();
        assertTrue(city.getVisited());
    }

    @Test
    void toggleVisitedWhenTrueTest() {
        city.toggleVisited();
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
    }


    @Test
    void toggleVisitedTrueTwiceTest() {
        city.toggleVisited();
        assertTrue(city.getVisited());
        city.toggleVisited();
        assertFalse(city.getVisited());
        city.toggleVisited();
        assertTrue(city.getVisited());
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
        City c1 = new City("test", 10, "none", "Iron islands", false);
        city.addAlliance(c1);
        assertEquals(1, c1.getAlliances().size());
        assertEquals(city, c1.getAlliances().get(0));
        assertEquals(1, city.getAlliances().size());
        assertEquals(c1, city.getAlliances().get(0));
    }

    @Test
    void addCityTwiceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false);
        City c2 = new City("test 2", 100, "none", "Crownlands", true);
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
    void addCityTwiceWithConnectedAllianceTest() {
        City c1 = new City("test 1", 10, "none", "Iron islands", false);
        City c2 = new City("test 2", 100, "none", "Crownlands", true);
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
