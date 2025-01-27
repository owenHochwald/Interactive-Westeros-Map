package model;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProgress {
    Progress p;
    City c1;
    City c2;


    @BeforeEach
    void setup() {
        c1 = new City("test 1", 10, "none", "Iron islands", false);
        c2 = new City("test 2", 100, "none", "Crownlands", true);
        p = new Progress();
    }

    @Test
    void progressConstructorTest() {
        assertEquals(2, p.getTotalNumCities());
        assertEquals(0, p.getNumVisited());
    }

    @Test
    void visitCityOnceTest() {
        assertEquals(0, p.getNumVisited());
        p.visitCity(c1);
        assertEquals(1, p.getNumVisited());
        assertTrue(c1.getVisited());
    }

    @Test
    void visitCityOnceWhenAlreadyTrueTest() {
        c1.toggleVisited();
        assertEquals(1, p.getNumVisited());
        p.visitCity(c1);
        assertEquals(1, p.getNumVisited());
        assertTrue(c1.getVisited());
    }

    @Test
    void visitTwoCitiesTest() {
        assertEquals(0, p.getNumVisited());
        p.visitCity(c1);
        p.visitCity(c2);
        assertEquals(2, p.getNumVisited());
        assertTrue(c1.getVisited());
        assertTrue(c2.getVisited());
    }

    @Test
    void setTotalNumCitiesTest() {
        p.setTotalNumCities(5);
        assertEquals(5, p.getTotalNumCities());

        p.setTotalNumCities(2);
        assertEquals(2, p.getTotalNumCities());
    }

}
