package model;

import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {
    private Customer testCustomer;
    private Car testCar;
    private LocalDate testPickup;
    private LocalDate testDropoff;
    private Rental testRental;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Ken", "Adams", 25);
        testCar = new Car("Honda", "Civic", 2020, true);
        testPickup = LocalDate.parse("2023-03-20");
        testDropoff = LocalDate.parse("2023-03-24");
        testRental = new Rental(testCustomer, testCar, testPickup, testDropoff);
    }

    @Test
    public void testConstructor() {
        //testCustomer already been tested in CustomerTest
        //testCar already been tested in CarTest

        assertEquals(testPickup, testRental.getPickup());
        assertEquals(testDropoff, testRental.getDropoff());
    }

    @Test
    public void testTotalCost() {
        //TODO
    }
}
