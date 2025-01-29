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
        Progress.setNumVisited(0);
        Progress.setTotalNumCities(0);
    }

    @Test
    void progressInitTest() {
        assertEquals(0, Progress.getTotalNumCities());
        assertEquals(0, Progress.getNumVisited());
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
        Progress.increaseNumVisited();
        assertEquals(1, Progress.getNumVisited());
    }

    
    @Test
    void increaseNumVisitedByTwoTest() {
        Progress.increaseNumVisited();
        Progress.increaseNumVisited();
        assertEquals(2, Progress.getNumVisited());
    }

    @Test
    void increaseNumVisitedByTwoAndDecreaseTest() {
        Progress.increaseNumVisited();
        Progress.increaseNumVisited();
        assertEquals(2, Progress.getNumVisited());

        Progress.decreaseNumVisited();
        assertEquals(1, Progress.getNumVisited());

        Progress.increaseNumVisited();
        assertEquals(2, Progress.getNumVisited());
    }

    @Test
    void decreaseNumVisitedByOneTest(){
        Progress.increaseNumVisited();
        assertEquals(1, Progress.getNumVisited());

        Progress.decreaseNumVisited();
        assertEquals(0, Progress.getNumVisited());
    }

    
    @Test
    void decreaseNumVisitedByTwoTest(){
        Progress.setNumVisited(3);
        assertEquals(3, Progress.getNumVisited());

        Progress.decreaseNumVisited();
        assertEquals(2, Progress.getNumVisited());
        Progress.decreaseNumVisited();
        assertEquals(1, Progress.getNumVisited());
    }

    @Test
    void resetProgressFromZeroTest() {
        assertEquals(0, Progress.getNumVisited());
        Progress.resetProgress();
        assertEquals(0, Progress.getNumVisited());
    }

    @Test
    void resetProgressFromNineTest() {
        assertEquals(0, Progress.getNumVisited());
        Progress.setNumVisited(9);
        assertEquals(9, Progress.getNumVisited());
        Progress.resetProgress();
        assertEquals(0, Progress.getNumVisited());
    }
}
