package model;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProgress {
    Progress p;


    @BeforeEach
    void setup() {
        Progress.setNumCitiesVisited(0);
        Progress.setTotalNumCities(0);
    }

    @Test
    void progressInitTest() {
        assertEquals(0, Progress.getTotalNumCities());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumEntries());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
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
    void increaseTotalNumEntriesByOneTest() {
        Progress.increaseNumEntries();
        assertEquals(1, Progress.getTotalNumEntries());
    }

    
    @Test
    void increaseTotalNumEntriesByTwoTest() {
        Progress.increaseNumEntries();
        Progress.increaseNumEntries();
        assertEquals(2, Progress.getTotalNumEntries());
    }

    @Test
    void increaseNumVisitedEntriesByOneTest() {
        Progress.increaseNumVisitedEntries();
        assertEquals(1, Progress.getTotalNumVisitedEntries());
    }

    
    @Test
    void increaseNumVisitedEntriesByTwoTest() {
        Progress.increaseNumVisitedEntries();
        Progress.increaseNumVisitedEntries();
        assertEquals(2, Progress.getTotalNumVisitedEntries());
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
    void decreaseNumEntriesVisitedByOneTest(){
        Progress.increaseNumVisitedEntries();
        assertEquals(1, Progress.getTotalNumVisitedEntries());

        Progress.decreasesNumVisitedEntries();
        assertEquals(0, Progress.getTotalNumVisitedEntries());
        assertEquals(1, Progress.getTotalNumEntries());

        
        Progress.decreasesNumEntries();
        assertEquals(0, Progress.getTotalNumEntries());
    }

    @Test
    void setEntriesOneVisitedTest() {
        Progress.setTotalNumVisited(1);
        assertEquals(1, Progress.getTotalNumEntries());
        Progress.setTotalNumVisited(10);
        assertEquals(10, Progress.getTotalNumEntries());
    }

    
    @Test
    void decreaseNumVisitedEntriesByTwoTest(){
        Progress.setTotalNumEntries(3);
        assertEquals(3, Progress.getTotalNumVisitedEntries());

        Progress.decreasesNumVisitedEntries();
        assertEquals(2, Progress.getTotalNumVisitedEntries());
        Progress.decreasesNumVisitedEntries();
        assertEquals(1, Progress.getTotalNumVisitedEntries());
    }

    @Test
    void resetProgressFromZeroEntriesTest() {
        assertEquals(0, Progress.getTotalNumEntries());
        Progress.resetEntryProgress();
        assertEquals(0, Progress.getTotalNumEntries());
    }

    @Test
    void resetProgressFromTenEntriesTest() {
        Progress.setTotalNumEntries(10);
        assertEquals(10, Progress.getTotalNumEntries());
        Progress.resetEntryProgress();
        assertEquals(0, Progress.getTotalNumEntries());
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
