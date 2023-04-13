package ui;

import model.BookingLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this part of the project is based on the ListDemo project from:
//          https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//represents the sequence of events that happen when the 'Cancel' button in the GUI is clicked
public class CancelButtonListener implements ActionListener {

    //fields
    private final RentalAppGUI rentalAppGUI;
    private JButton button;

    //CONSTRUCTOR
    public CancelButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    //EFFECTS: removes booking from GUI panel
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = rentalAppGUI.bookingLog.getSelectedIndex();

        //if booking is not selected, display message box. if booking is selected, remove from BookingLog panel in GUI
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a booking to cancel.");
        } else {

            //remove the booking from the booking list in the GUI
            BookingLog.removeBookingFromList(index);
            rentalAppGUI.listModel.remove(index);

            if (index == rentalAppGUI.listModel.getSize()) {
                index--;
            }
            rentalAppGUI.bookingLog.setSelectedIndex(index);
            rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
        }
    }
}
