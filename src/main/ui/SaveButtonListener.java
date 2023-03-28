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

public class SaveButtonListener implements ActionListener {

    private static final String DESTINATION = "./data/bookinglog.json";
    private RentalAppGUI rentalAppGUI;
    private JButton button;
    private JsonWriter jsonWriter;

    public SaveButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter = new JsonWriter(DESTINATION);
            jsonWriter.open();
            JSONArray ja = new JSONArray();
            List<Booking> bookings = rentalAppGUI.getBookingList();
            for (Booking booking : bookings) {
                JSONObject jo = booking.toJson();
                System.out.println("json obj: " + jo.toString());
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
