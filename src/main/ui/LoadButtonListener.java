package ui;

import model.Booking;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//this is what happens with the 'Load' button in the GUI is clicked
//for every booking in the file, it will read it, convert it to Booking, then add it to BookingLog panel in the GUI
public class LoadButtonListener implements ActionListener {

    private static final String DESTINATION = "./data/bookinglog.json";
    private RentalAppGUI rentalAppGUI;
    private JButton button;
    private JsonReader jsonReader;

    //CONSTRUCTOR
    public LoadButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    //EFFECTS: reads booking from file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonReader = new JsonReader(DESTINATION);
            JSONArray ja = jsonReader.read(DESTINATION);
            rentalAppGUI.clearBookingList();
            for (int i = 0; i < ja.length(); i++) {
                Booking booking = (Booking) ja.get(i);
                rentalAppGUI.listModel.addElement(booking.getFirstName() + " " + booking.getLastName());
                rentalAppGUI.addBookingToList(booking);
            }
            JOptionPane.showMessageDialog(null, "LOADED FROM: " + DESTINATION);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "CANNOT READ FROM FILE: " + DESTINATION);
        }
    }
}
