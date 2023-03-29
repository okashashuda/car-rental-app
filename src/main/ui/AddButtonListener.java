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

    //if none of the fields are empty, then once the 'Add' button is clicked,
    //it creates a booking with user info and clears all the fields for the next booking to be created
    //it also creates a pop-up window that displays a random picture (this is to satisfy phase 3 requirements)
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = rentalAppGUI.bookingLog.getSelectedIndex();

        //checks if any of the fields are empty.
        //if yes, displays message box
        if (rentalAppGUI.firstNameField.getText().isEmpty() || rentalAppGUI.lastNameField.getText().isEmpty()
                || rentalAppGUI.ageField.getText().isEmpty() || rentalAppGUI.pickupDateField.getText().isEmpty()
                || rentalAppGUI.dropoffDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields");
        } else {
            Booking booking = getBooking();

            visualComponent(); //image appears when booking is successfully to BookingList

            rentalAppGUI.addBookingToList(booking); //add the booking to the booking log list in the GUI

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

    //this the visual component (image) for phase 3 requirement.
    //creates a new frame, and displays a chosen image onto it
    private void visualComponent() {
        JFrame picture = new JFrame();
        ImageIcon visual = new ImageIcon("data/meme.PNG");
        picture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        picture.setSize(visual.getIconWidth(), visual.getIconHeight());
        picture.add(new JLabel(visual));
        picture.setVisible(true);
    }

    //create a new booking from user input
    private Booking getBooking() {
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
