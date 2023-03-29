package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import model.Booking;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWriter;

//this is what happens when the 'Save' button in the GUI is clicked
//for every booking in the BookingLog panel in the GUI, it will convert it to JSON format, then write it to file
public class SaveButtonListener implements ActionListener {

    private static final String DESTINATION = "./data/bookinglog.json";
    private RentalAppGUI rentalAppGUI;
    private JButton button;
    private JsonWriter jsonWriter;

    //CONSTRUCTOR
    public SaveButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    //EFFECTS: saves booking to file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter = new JsonWriter(DESTINATION);
            jsonWriter.open();
            JSONArray ja = new JSONArray();
            List<Booking> bookings = rentalAppGUI.getBookingList();
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
