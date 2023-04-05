package ui;

import model.Booking;
import model.BookingLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this part of the project is based on the ListDemo project from:
//          https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//this is what happens when the 'Add' button in the GUI is clicked
//if none of the fields are empty, it will add to BookingLog panel in the GUI
//then it will clear all the fields for the next booking to be created
public class AddButtonListener implements ActionListener {

    private final RentalAppGUI rentalAppGUI;
    private JButton button;

    //CONSTRUCTOR
    public AddButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    //creates booking with user info, clears all fields and displays picture in pop-up window (for phase 3 requirement)
    //EFFECTS: adds booking to GUI panel
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = rentalAppGUI.bookingLog.getSelectedIndex();

        //checks if any of the fields are empty. if yes, displays message box
        if (rentalAppGUI.firstNameField.getText().isEmpty() || rentalAppGUI.lastNameField.getText().isEmpty()
                || rentalAppGUI.ageField.getText().isEmpty() || rentalAppGUI.pickupDateField.getText().isEmpty()
                || rentalAppGUI.dropoffDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields");
        } else {
            Booking booking = getBooking();

            //image appears when booking is successfully to BookingList
            visualComponent();

            //add the booking to the booking list in the GUI
            BookingLog.addBookingToList(booking);
            rentalAppGUI.listModel.addElement(
                    rentalAppGUI.firstNameField.getText() + " " + rentalAppGUI.lastNameField.getText());

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
    //EFFECTS: creates a new frame, and displays a chosen image onto it
    private void visualComponent() {
        JFrame picture = new JFrame();
        ImageIcon visual = new ImageIcon("data/meme.PNG");
        picture.setDefaultCloseOperation(picture.HIDE_ON_CLOSE);
        picture.setSize(visual.getIconWidth(), visual.getIconHeight());
        picture.setLocation(600, 300);
        picture.add(new JLabel(visual));
        picture.setVisible(true);
    }

    //EFFECTS: create a new booking from user input
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
