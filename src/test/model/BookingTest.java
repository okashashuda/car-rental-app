package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking testBooking;
    private String testFirstName;
    private String testLastName;
    private int testAge;
    private String testCar;
    private String testPickupDate;
    private String testDropoffDate;
    private JSONObject testJson;

    @BeforeEach
    public void setUp() {
        testFirstName = "Ken";
        testLastName = "Adams";
        testAge = 25;
        testCar = "2020 Honda Civic";
        testPickupDate = "2023-03-03";
        testDropoffDate = "2023-03-05";
        testBooking = new Booking(testFirstName, testLastName, testAge, testCar, testPickupDate, testDropoffDate);
        testJson = testBooking.toJson();
    }

    @Test
    public void testConstructor() {
        assertEquals("Ken", testBooking.getFirstName());
        assertEquals("Adams", testBooking.getLastName());
        assertEquals(25, testBooking.getAge());
        assertEquals("2020 Honda Civic", testBooking.getCar());
        assertEquals(LocalDate.parse("2023-03-03"), testBooking.getPickupDate());
        assertEquals(LocalDate.parse("2023-03-05"), testBooking.getDropoffDate());
    }

    @Test
    public void testToJson() {
        assertEquals("Ken", testJson.getString("firstName"));
        assertEquals("Adams", testJson.getString("lastName"));
        assertEquals(25, testJson.getInt("age"));
        assertEquals("2020 Honda Civic", testJson.getString("car"));
        assertEquals("2023-03-03", testJson.getString("pickup"));
        assertEquals("2023-03-05", testJson.getString("dropoff"));
    }
}
