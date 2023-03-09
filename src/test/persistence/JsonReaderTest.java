package persistence;

import model.BookingLog;
import model.Rental;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    //idea taken from JsonSerializationDemo
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookingLog bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // passes if an IOException is thrown
        }
    }

    //idea taken from JsonSerializationDemo
    @Test
    public void testReaderEmptyBookingLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookingLog.json");
        try {
            BookingLog bl = reader.read();
            assertEquals(0, bl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    //idea taken from JsonSerializationDemo
    @Test
    public void testReaderGeneralBookingLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookingLog.json");
        try {
            BookingLog bl = reader.read();
            List<Rental> bookingsList = bl.getAllBookings();
            assertEquals(1, bl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
