package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event eventTestObject;
    private Date dateTestObjec;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        eventTestObject = new Event("Sensor open at door"); // (1)
        dateTestObjec = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", eventTestObject.getDescription());
        assertEquals(dateTestObjec, eventTestObject.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(dateTestObjec.toString() + "\n" + "Sensor open at door", eventTestObject.toString());
    }

    @Test
    public void testEquals() {
        Event event1 = new Event("Test Event 1");
        Event event2 = new Event("Test Event 2");
        Event event3 = new Event("Test Event 1");

        assertFalse(event1.equals(null));
        assertFalse(event1.equals(event2));
        assertTrue(event1.equals(event3));
    }

    @Test
    public void testHashCode() {
        Event event1 = new Event("Test Event 1");
        Event event2 = new Event("Test Event 2");
        Event event3 = new Event("Test Event 1");

        assertEquals(event1.hashCode(), event3.hashCode());

        assertNotEquals(event1.hashCode(), event2.hashCode());
    }
}
