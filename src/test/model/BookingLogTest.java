package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingLogTest {

    private BookingLog testBookings;
    private Customer testCustomer1;
    private Customer testCustomer2;
    private Car testCar1;
    private Car testCar2;
    private LocalDate testPickup1;
    private LocalDate testPickup2;
    private LocalDate testDropoff1;
    private LocalDate testDropoff2;
    private Rental testRental1;
    private Rental testRental2;

    @BeforeEach
    public void setUp() {
        testBookings = new BookingLog();
        testCustomer1 = new Customer("Ken", "Adams", 25);
        testCustomer2 = new Customer("Joe", "Black", 23);
        testCar1 = new Car("Honda", "Civic", 2020, true);
        testCar2 = new Car("Toyota", "Camry", 2022, true);
        testPickup1 = LocalDate.parse("2023-03-20");
        testPickup2 = LocalDate.parse("2023-04-20");
        testDropoff1 = LocalDate.parse("2023-03-24");
        testDropoff2 = LocalDate.parse("2023-04-20");
        testRental1 = new Rental(testCustomer1, testCar1, testPickup1, testDropoff1);
        testRental2 = new Rental(testCustomer2, testCar2, testPickup2, testDropoff2);
    }

    @Test
    public void testConstructor() {
        //Customer, Car, and Rental already been tested in their respective test classes

        assertEquals(0, testBookings.size());
    }

    @Test
    public void testSetBookingID() {
        testBookings.addRental(testRental1);
        assertEquals(0, testBookings.getPosition(testRental1));
        assertEquals(1, testBookings.getBookingID(testBookings.getPosition(testRental1)));
        testBookings.addRental(testRental2);
        assertEquals(1, testBookings.getPosition(testRental2));
        assertEquals(2, testBookings.getBookingID(testBookings.getPosition(testRental2)));
    }

    @Test
    public void testAddRental() {
        assertEquals(0, testBookings.size());
        testBookings.addRental(testRental1);
        assertEquals(1, testBookings.size());
    }

    @Test
    public void testAddMultipleRental() {
        assertEquals(0, testBookings.size());
        testBookings.addRental(testRental1);
        assertEquals(1, testBookings.size());
        testBookings.addRental(testRental2);
        assertEquals(2, testBookings.size());
    }

    @Test
    public void testCancelRental() {
        testBookings.addRental(testRental1);
        testBookings.addRental(testRental2);
        assertEquals(2, testBookings.size());
        testBookings.cancelRental(1);
        assertEquals(1, testBookings.size());
        assertEquals(2, testBookings.getBookingID(1));
    }

//    @Test
//    public void testViewRental() {
//
//    }
//
//    @Test
//    public void testEditRental() {
//
//    }

    @Test
    public void testGetAllBookings() {
        testBookings.addRental(testRental1);
        assertEquals(testBookings.getAllBookings().size(), 1);
        testBookings.addRental(testRental2);
        assertEquals(testBookings.getAllBookings().size(), 2);
    }
}
