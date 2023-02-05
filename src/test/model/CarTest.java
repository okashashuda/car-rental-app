package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//unit tests for Car class
class CarTest {
    private Car testCar;

    @BeforeEach
    public void setUp() {
        testCar = new Car("Honda", "Civic", 2020, true);
    }

    @Test
    public void testConstructor() {
        assertEquals("Honda", testCar.getMake());
        assertEquals("Civic", testCar.getModel());
        assertEquals(2020, testCar.getYear());
        assertTrue(testCar.isAvailable());
    }



}