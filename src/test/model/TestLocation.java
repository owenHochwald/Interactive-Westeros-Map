package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestLocation {
    private Location loc;

    @BeforeEach
    void setup() {
        loc = new Location("Kings Road", "Crownlands", false, true);
    }

    @Test
    void constructorTest() {
        assertEquals("Kings Road", loc.getName());
        assertEquals("Crownlands", loc.getRegion());
        assertFalse(loc.getVisited());
        assertTrue(loc.customMade());
        assertEquals(0, Progress.getNumVisited());
    }

    @Test
    void toggleVisitedWhenFalseTest() {
        assertFalse(loc.getVisited());
        assertEquals(0, Progress.getNumVisited());
        loc.toggleVisited();
        assertEquals(1, Progress.getNumVisited());
        assertTrue(loc.getVisited());
    }

    @Test
    void toggleVisitedWhenTrueTest() {
        loc.toggleVisited();
        assertEquals(1, Progress.getNumVisited());
        assertTrue(loc.getVisited());
        loc.toggleVisited();
        assertFalse(loc.getVisited());
        assertEquals(0, Progress.getNumVisited());
    }
    
}
