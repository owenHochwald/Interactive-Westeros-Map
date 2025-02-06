package model;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProgress {
    Progress p;


    @BeforeEach
    void setup() {
        // c1 = new City("test 1", 10, "none", "Iron islands", false);
        // c2 = new City("test 2", 100, "none", "Crownlands", true);
        // p = new Progress();
        Progress.setNumCitiesVisited(0);
        Progress.setTotalNumCities(0);
    }

    @Test
    void progressInitTest() {
        assertEquals(0, Progress.getTotalNumCities());
        assertEquals(0, Progress.getNumCitiesVisited());
    }

    @Test
    void increaseTotalNumCitiesByOneTest() {
        Progress.increaseTotalCities();
        assertEquals(1, Progress.getTotalNumCities());
    }

    
    @Test
    void increaseTotalNumCitiesByTwoTest() {
        Progress.increaseTotalCities();
        Progress.increaseTotalCities();
        assertEquals(2, Progress.getTotalNumCities());
    }

    @Test
    void increaseNumVisitedByOneTest() {
        Progress.increaseNumCitiesVisited();
        assertEquals(1, Progress.getNumCitiesVisited());
    }

    
    @Test
    void increaseNumVisitedByTwoTest() {
        Progress.increaseNumCitiesVisited();
        Progress.increaseNumCitiesVisited();
        assertEquals(2, Progress.getNumCitiesVisited());
    }

    @Test
    void increaseNumVisitedByTwoAndDecreaseTest() {
        Progress.increaseNumCitiesVisited();
        Progress.increaseNumCitiesVisited();
        assertEquals(2, Progress.getNumCitiesVisited());

        Progress.decreaseNumCitiesVisited();
        assertEquals(1, Progress.getNumCitiesVisited());

        Progress.increaseNumCitiesVisited();
        assertEquals(2, Progress.getNumCitiesVisited());
    }

    @Test
    void decreaseNumVisitedByOneTest(){
        Progress.increaseNumCitiesVisited();
        assertEquals(1, Progress.getNumCitiesVisited());

        Progress.decreaseNumCitiesVisited();
        assertEquals(0, Progress.getNumCitiesVisited());
    }

    
    @Test
    void decreaseNumVisitedByTwoTest(){
        Progress.setNumCitiesVisited(3);
        assertEquals(3, Progress.getNumCitiesVisited());

        Progress.decreaseNumCitiesVisited();
        assertEquals(2, Progress.getNumCitiesVisited());
        Progress.decreaseNumCitiesVisited();
        assertEquals(1, Progress.getNumCitiesVisited());
    }

    @Test
    void resetProgressFromZeroTest() {
        assertEquals(0, Progress.getNumCitiesVisited());
        Progress.resetCityProgress();
        assertEquals(0, Progress.getNumCitiesVisited());
    }

    @Test
    void resetProgressFromNineTest() {
        assertEquals(0, Progress.getNumCitiesVisited());
        Progress.setNumCitiesVisited(9);
        assertEquals(9, Progress.getNumCitiesVisited());
        Progress.resetCityProgress();
        assertEquals(0, Progress.getNumCitiesVisited());
    }
}
