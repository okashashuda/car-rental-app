package ui;

import model.Booking;
import model.BookingLog;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

//represents the sequence of events that happen when the 'Save' button in the GUI is clicked
public class SaveButtonListener implements ActionListener {

    //fields
    private static final String DESTINATION = "./data/bookinglog.json";
    private RentalAppGUI rentalAppGUI;
    private JButton button;
    private JsonWriter jsonWriter;

    //CONSTRUCTOR
    public SaveButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    //for each booking in BookingLog panel in GUI, convert to JSON format, then saves/writes booking to file
    //EFFECTS: saves booking to file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter = new JsonWriter(DESTINATION);
            jsonWriter.open();
            JSONArray ja = new JSONArray();
            List<Booking> bookings = BookingLog.getBookingList();
            for (Booking booking : bookings) {
                JSONObject jo = booking.toJson();
                //System.out.println("json obj: " + jo.toString());
                ja.put(jo);
            }
            jsonWriter.write(ja);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "SAVED TO: " + DESTINATION);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "CANNOT WRITE TO FILE: " + DESTINATION);
        }
    }
}
