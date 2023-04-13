package persistence;

import model.BookingLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

//idea taken from JsonSerializationDemo
//Represents a writer that writes JSON representation of booking to file
public class JsonWriter {

    //constants
    private static final int TAB = 4;

    //fields
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //         be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of workroom to file
    public void write(BookingLog bl) {
        JSONObject json = bl.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of workroom to file based on the bookings from GUI
    public void write(JSONArray bookings) {
        JSONArray json = bookings;
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

