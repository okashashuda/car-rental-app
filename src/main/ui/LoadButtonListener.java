package ui;

import model.Booking;
import model.BookingLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadButtonListener implements ActionListener {

    private static final String DESTINATION = "./data/bookinglog.json";
    private RentalAppGUI rentalAppGUI;
    private JButton button;
    private JsonReader jsonReader;

    public LoadButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonReader = new JsonReader(DESTINATION);
            JSONArray ja = jsonReader.read(DESTINATION);
            rentalAppGUI.clearBookingList();
            for (int i = 0; i < ja.length(); i++) {
                Booking booking = (Booking) ja.get(i);
                rentalAppGUI.listModel.addElement(booking.getFirstName() + " " + booking.getLastName());
                rentalAppGUI.bookingList.add(booking);
            }
            JOptionPane.showMessageDialog(null, "LOADED FROM: " + DESTINATION);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "CANNOT READ FROM FILE: " + DESTINATION);
        }
    }
}
