package persistence;

import model.BookingLog;
import model.Car;
import model.Customer;
import model.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    private Rental testRental;

    @BeforeEach
    public void setUp() {
        Customer testCustomer = new Customer("Ken", "Adams", 25);
        Car testCar = new Car("Honda", "Civic", 2020);
        LocalDate testPickup = LocalDate.parse("2023-03-20");
        LocalDate testDropoff = LocalDate.parse("2023-03-24");
        testRental = new Rental(testCustomer, testCar, testPickup, testDropoff);
    }

    //idea taken from JsonSerializationDemo
    @Test
    public void testWriterInvalidFile() {
        try {
            BookingLog bl = new BookingLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // passes if IOException is thrown
        }
    }

    //idea taken from JsonSerializationDemo
    @Test
    public void testWriterEmptyBooking() {
        try {
            BookingLog bl = new BookingLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookingLog.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookingLog.json");
            bl = reader.read();
            assertEquals(0, bl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //idea taken from JsonSerializationDemo
    @Test
    public void testWriterGeneralBooking() {
        try {
            BookingLog bl = new BookingLog();
            bl.addRental(testRental);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookingLog.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookingLog.json");
            bl = reader.read();
            List<Rental> rentals = bl.getAllBookings();
            assertEquals(1, bl.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
