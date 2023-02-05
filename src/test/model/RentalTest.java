package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {
    private Rental testRental;

    @BeforeEach
    public void setUp() {
        testRental = new Rental("YVR Airport", "YXX Airport", 4);
    }

    @Test
    public void testConstructor() {
        assertEquals("YVR Airport", testRental.getPickup());
        assertEquals("YXX Airport", testRental.getDropoff());
        assertEquals(4, testRental.getNumOfDays());
    }
}
