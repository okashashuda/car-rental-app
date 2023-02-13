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
    private LocalDate testUpdatedDropoff2;
    private Rental testRental1;
    private Rental testRental2;
    private Rental testUpdatedRental2;

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
        testDropoff2 = LocalDate.parse("2023-04-24");
        testUpdatedDropoff2 = LocalDate.parse("2023-04-25");
        testRental1 = new Rental(testCustomer1, testCar1, testPickup1, testDropoff1);
        testRental2 = new Rental(testCustomer2, testCar2, testPickup2, testDropoff2);
        testUpdatedRental2 = new Rental(testCustomer2, testCar2, testPickup2, testUpdatedDropoff2);
    }

    @Test
    public void testConstructor() {
        //Customer, Car, and Rental already been tested in their respective test classes
        assertEquals(0, testBookings.getSize());
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
        assertEquals(0, testBookings.getSize());
        testBookings.addRental(testRental1);
        assertEquals(1, testBookings.getSize());
    }

    @Test
    public void testAddMultipleRental() {
        assertEquals(0, testBookings.getSize());
        testBookings.addRental(testRental1);
        assertEquals(1, testBookings.getSize());
        testBookings.addRental(testRental2);
        assertEquals(2, testBookings.getSize());
    }

    @Test
    public void testCancelRental() {
        testBookings.addRental(testRental1); //position 0, bookingID 1
        testBookings.addRental(testRental2); //position 1, bookingID 2
        assertEquals(2, testBookings.getSize());
        testBookings.cancelRental(1); //removes booking that was at position 0 (bookingID 1)
        assertEquals(1, testBookings.getSize()); //only booking that was in position 1 (bookingID 2) remains
        assertEquals(2, testBookings.getBookingID(1)); //bookingID is 2 of booking at position 1
        assertEquals(0, testBookings.getPosition(testRental2));
    }

    @Test
    public void testViewRental() {
        testBookings.addRental(testRental1); //position 0, bookingID 1
        testBookings.addRental(testRental2); //position 1, bookingID 2
        assertEquals(testRental1, testBookings.getAllBookings().get(0));
        assertEquals(testRental2, testBookings.getAllBookings().get(1));
        assertEquals(1, testBookings.getBookingID(0));
        assertEquals(testRental1, testBookings.viewRental(1));
        assertEquals(testRental2, testBookings.viewRental(2));
    }

    @Test
    public void testEditRental() {
        testBookings.addRental(testRental1); //position 0, bookingID 1
        testBookings.addRental(testRental2); //position 1, bookingID 2
        testBookings.editRental(2, testUpdatedRental2);
        assertEquals(testRental1, testBookings.getAllBookings().get(0));
        assertEquals(testUpdatedRental2, testBookings.getAllBookings().get(1));
    }

    @Test
    public void testGetAllBookings() {
        testBookings.addRental(testRental1);
        assertEquals(1, testBookings.getAllBookings().size());
        testBookings.addRental(testRental2);
        assertEquals(2, testBookings.getAllBookings().size());
    }
}
