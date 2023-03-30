package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this part of the project is based on the ListDemo project from:
//          https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//this is what happens when the 'Cancel' button in the GUI is clicked
//if a booking is selected, it will remove it from the BookingLog panel in the GUI
public class CancelButtonListener implements ActionListener {

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

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a booking to cancel.");
        } else {

            //remove the booking from the booking list in the GUI
            rentalAppGUI.listModel.remove(index);
            rentalAppGUI.removeBookingFromList(index);

            if (index == rentalAppGUI.listModel.getSize()) {
                index--;
            }
            rentalAppGUI.bookingLog.setSelectedIndex(index);
            rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
        }
    }
}
