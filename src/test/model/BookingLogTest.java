package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingLogTest {
    private BookingLog testRentals;
    private BookingLog testBookingList; //testBookingList is from GUI, testRentals is from console UI
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
    private Booking testBooking; //different from testRental(s), testBooking is from GUI, testRental is from console UI
    private JSONObject testJsonObject;
    private JSONArray testJsonArray;

    @BeforeEach
    public void setUp() {
        testRentals = new BookingLog();
        testBookingList = new BookingLog();
        testCustomer1 = new Customer("Ken", "Adams", 25);
        testCustomer2 = new Customer("Joe", "Black", 23);
        testCar1 = new Car("Honda", "Civic", 2020);
        testCar2 = new Car("Toyota", "Camry", 2022);
        testPickup1 = LocalDate.parse("2023-03-20");
        testPickup2 = LocalDate.parse("2023-04-20");
        testDropoff1 = LocalDate.parse("2023-03-24");
        testDropoff2 = LocalDate.parse("2023-04-24");
        testUpdatedDropoff2 = LocalDate.parse("2023-04-25");
        testRental1 = new Rental(testCustomer1, testCar1, testPickup1, testDropoff1);
        testRental2 = new Rental(testCustomer2, testCar2, testPickup2, testDropoff2);
        testUpdatedRental2 = new Rental(testCustomer2, testCar2, testPickup2, testUpdatedDropoff2);
        testBooking = new Booking("Ken", "Adams", 33,
                "2020 Honda Civic", "2023-03-20", "2023-03-24");
    }

    @Test
    public void testConstructor() {
        //Customer, Car, and Rental already been tested in their respective test classes
        assertEquals(0, testRentals.getSize());
    }

    @Test
    public void testGetBookingID() {
        testRentals.addRental(testRental1);
        assertEquals(0, testRentals.getPosition(testRental1));
        assertEquals(1, testRentals.getBookingID(testRentals.getPosition(testRental1)));
        testRentals.addRental(testRental2);
        assertEquals(1, testRentals.getPosition(testRental2));
        assertEquals(2, testRentals.getBookingID(testRentals.getPosition(testRental2)));
    }

    @Test
    public void testAddRental() {
        assertEquals(0, testRentals.getSize());
        testRentals.addRental(testRental1);
        assertEquals(1, testRentals.getSize());
    }

    @Test
    public void testAddMultipleRental() {
        assertEquals(0, testRentals.getSize());
        testRentals.addRental(testRental1);
        assertEquals(1, testRentals.getSize());
        testRentals.addRental(testRental2);
        assertEquals(2, testRentals.getSize());
    }

    @Test
    public void testToString() {
        testRentals.addRental(testRental1);
        assertEquals("Booking Log: \n" + testRentals.getBookingID(testRentals.getPosition(testRental1))
                + ". " + "Rental: \n -Customer: " + testCustomer1.getFirstName() + " " + testCustomer1.getLastName()
                + "\n" + " -Car: " + testCar1.getMake() + " " + testCar1.getModel()
                + "\n" + " -Pickup: " + testPickup1
                + "\n" + " -Dropoff: " + testDropoff1 + "\n\n", testRentals.toString());
    }

    @Test
    public void testCancelRental() {
        testRentals.addRental(testRental1); //position 0, bookingID 1
        testRentals.addRental(testRental2); //position 1, bookingID 2
        assertEquals(2, testRentals.getSize());
        testRentals.cancelRental(1); //removes booking that was at position 0 (bookingID 1)
        assertEquals(1, testRentals.getSize()); //only booking that was in position 1 (bookingID 2) remains
        assertEquals(2, testRentals.getBookingID(1)); //bookingID is 2 of booking at position 1
        assertEquals(0, testRentals.getPosition(testRental2));
    }

    @Test
    public void testViewRental() {
        testRentals.addRental(testRental1); //position 0, bookingID 1
        testRentals.addRental(testRental2); //position 1, bookingID 2
        assertEquals(testRental1, testRentals.getAllBookings().get(0));
        assertEquals(testRental2, testRentals.getAllBookings().get(1));
        assertEquals(1, testRentals.getBookingID(0));
        assertEquals(testRental1, testRentals.viewRental(1));
        assertEquals(testRental2, testRentals.viewRental(2));
    }

    @Test
    public void testEditRental() {
        testRentals.addRental(testRental1); //position 0, bookingID 1
        testRentals.addRental(testRental2); //position 1, bookingID 2
        testRentals.editRental(2, testUpdatedRental2); //want to update bookingID 2 with UpdatedRental2
        assertEquals(testRental1, testRentals.getAllBookings().get(0)); //testRental1 is unchanged
        assertEquals(testUpdatedRental2, testRentals.getAllBookings().get(1)); //testRental2 has been edited
    }

    @Test
    public void testGetAllBookings() {
        testRentals.addRental(testRental1);
        assertEquals(1, testRentals.getAllBookings().size());
        testRentals.addRental(testRental2);
        assertEquals(2, testRentals.getAllBookings().size());
    }

    @Test
    public void testToJson() {
        testRentals.addRental(testRental1);
        testJsonObject = testRentals.toJson();
        testJsonArray = testRentals.toJson().getJSONArray("rentals");
        assertEquals(1, testJsonArray.length());
        assertEquals(testRental1.toJson().toString(), testJsonArray.getJSONObject(0).toString());
        //the .toString() converts both into JSON text so comparing them is easier.
        testRentals.addRental(testRental2);
        testJsonObject = testRentals.toJson();
        testJsonArray = testRentals.toJson().getJSONArray("rentals");
        assertEquals(2, testJsonArray.length());
        assertEquals(testRental2.toJson().toString(), testJsonArray.getJSONObject(1).toString());
    }

    @Test
    public void testAddBookingToList() {
        testBookingList.addBookingToList(testBooking);
        assertEquals(1, testBookingList.getBookingList().size());
    }

    @Test
    public void testRemoveBookingFromList() {
        testBookingList.addBookingToList(testBooking);
        assertEquals(1, testBookingList.getBookingList().size());
        testBookingList.removeBookingFromList(0);
        assertEquals(0, testBookingList.getBookingList().size());
    }

    @Test
    public void testClearBookingList() {
        testBookingList.addBookingToList(testBooking);
        assertEquals(1, testBookingList.getBookingList().size());
        testBookingList.clearBookingList();
        assertEquals(0, testBookingList.getBookingList().size());
    }
}
