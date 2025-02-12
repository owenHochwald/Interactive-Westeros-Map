package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestLocation {
    private Location loc;

    @Before
    public void setup() {
        Progress.setNumCitiesVisited(0);
        Progress.resetEntryProgress();
        Progress.resetTotalEntry();
        loc = new Location("Kings Road", "Crownlands", true);
    }

    @Test
    public void constructorTest() {
        Progress.resetTotalCities();
        assertEquals("Kings Road", loc.getName());
        assertEquals("Crownlands", loc.getRegion());
        assertTrue(loc.customMade());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumCities());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
        assertEquals(1, Progress.getTotalNumEntries());
    }

    @Test
    public void toggleVisitedWhenFalseTest() {
        assertFalse(loc.getVisited());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumEntries());
        assertEquals(0, Progress.getTotalNumVisitedEntries());

        loc.toggleVisited();
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumEntries());
        assertEquals(1, Progress.getTotalNumVisitedEntries());

        assertTrue(loc.getVisited());
    }

    @Test
    public void toggleVisitedWhenTrueTest() {
        loc.toggleVisited();
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(1, Progress.getTotalNumVisitedEntries());
        assertTrue(loc.getVisited());
        loc.toggleVisited();
        assertFalse(loc.getVisited());
        assertEquals(0, Progress.getNumCitiesVisited());
        assertEquals(0, Progress.getTotalNumVisitedEntries());
    }
    
}
