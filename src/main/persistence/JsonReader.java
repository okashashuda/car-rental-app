package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

//idea taken from JsonSerializationDemo
//Represents a reader that reads JSON representation of booking from file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads booking log from file and returns it;
    //         throws IOException if an error occurs reading data from file
    public BookingLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookingLog(jsonObject);
    }

    //EFFECTS: read the JSONArray (which is the .json file) and returns it;
    //         throws IOException if an error occurs reading data from file
    public JSONArray read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONArray bookings = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Booking booking = parseBooking(jsonObject);
            bookings.put(booking);
        }
        return bookings;
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s));
        }
        return sb.toString();
    }

    //EFFECTS: parses booking log from JSON object and returns it
    private BookingLog parseBookingLog(JSONObject jsonObject) {
        BookingLog bl = new BookingLog();
        JSONArray rentalArray = jsonObject.getJSONArray("rentals");
        for (Object rentalObj : rentalArray) {
            JSONObject nextRental = (JSONObject) rentalObj;
            Rental rental = parseRental(nextRental);
            bl.addRental(rental);
        }
        return bl;
    }

    //EFFECTS: parses rental from JSON object and returns it
    private Rental parseRental(JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        int age = jsonObject.getInt("age");
        Customer customer = new Customer(firstName, lastName, age);

        String make = jsonObject.getString("make");
        String model = jsonObject.getString("model");
        int year = jsonObject.getInt("year");
        Car car = new Car(make, model, year);

        String pickupStr = jsonObject.getString("pickup");
        LocalDate pickup = LocalDate.parse(pickupStr);
        String dropoffStr = jsonObject.getString("dropoff");
        LocalDate dropoff = LocalDate.parse(dropoffStr);

        return new Rental(customer, car, pickup, dropoff);
    }

    //EFFECTS: parses booking (which is each individual booking from GUI) from JSON object and returns it
    private Booking parseBooking(JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        int age = jsonObject.getInt("age");
        String car = jsonObject.getString("car");
        String pickup = jsonObject.getString("pickup");
        String dropoff = jsonObject.getString("dropoff");

        return new Booking(firstName, lastName, age, car, pickup, dropoff);
    }
}
