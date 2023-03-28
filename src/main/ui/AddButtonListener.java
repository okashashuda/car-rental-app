package ui;

import model.Booking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {

    private final RentalAppGUI rentalAppGUI;
    private JButton button;

    public AddButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = rentalAppGUI.bookingLog.getSelectedIndex();

        if (rentalAppGUI.firstNameField.getText().isEmpty() || rentalAppGUI.lastNameField.getText().isEmpty()
                || rentalAppGUI.ageField.getText().isEmpty() || rentalAppGUI.pickupDateField.getText().isEmpty()
                || rentalAppGUI.dropoffDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields");
        } else {
            Booking booking = getBooking();

            // Add the booking to the booking log list in the GUI
            rentalAppGUI.addBookingToList(booking);

            rentalAppGUI.listModel.addElement(rentalAppGUI.firstNameField.getText() + " "
                    + rentalAppGUI.lastNameField.getText());
            rentalAppGUI.firstNameField.setText("");
            rentalAppGUI.lastNameField.setText("");
            rentalAppGUI.ageField.setText("");
            rentalAppGUI.pickupDateField.setText("");
            rentalAppGUI.dropoffDateField.setText("");
            rentalAppGUI.bookingLog.setSelectedIndex(index);
            rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
        }
    }

    private Booking getBooking() {
        // Create a new booking from user input
        Booking booking = new Booking(
                rentalAppGUI.firstNameField.getText(),
                rentalAppGUI.lastNameField.getText(),
                Integer.parseInt(rentalAppGUI.ageField.getText()),
                rentalAppGUI.carField.getSelectedItem().toString(),
                rentalAppGUI.pickupDateField.getText(),
                rentalAppGUI.dropoffDateField.getText()
        );
        return booking;
    }
}
