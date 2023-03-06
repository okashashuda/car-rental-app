package persistence;

import model.BookingLog;
import model.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookingLog bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookingLog.json");
        try {
            BookingLog bl = reader.read();
            assertEquals(0, bl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookingLog.json");
        try {
            BookingLog bl = reader.read();
            List<Rental> rentals = bl.getAllBookings();
            assertEquals(1, bl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
