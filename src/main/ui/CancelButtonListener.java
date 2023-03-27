package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonListener implements ActionListener {

    private final RentalAppGUI rentalAppGUI;
    private JButton button;

    public CancelButtonListener(RentalAppGUI rentalAppGUI, JButton button) {
        this.rentalAppGUI = rentalAppGUI;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = rentalAppGUI.bookingLog.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a booking to cancel.");
        } else {
            rentalAppGUI.listModel.remove(index);

            if (rentalAppGUI.listModel.isEmpty()) {
                rentalAppGUI.cancelButton.setEnabled(false);
            } else {
                if (index == rentalAppGUI.listModel.getSize()) {
                    index--;
                }
                rentalAppGUI.bookingLog.setSelectedIndex(index);
                rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
            }
        }


//        if (rentalAppGUI.listModel.isEmpty()) {
//            rentalAppGUI.cancelButton.setEnabled(false);
//        } else {
//            if (index == rentalAppGUI.listModel.getSize()) {
//                index--;
//            } else if (index == -1) {
//                JOptionPane.showMessageDialog(null, "Please select a booking to cancel.");
//            }
//            rentalAppGUI.bookingLog.setSelectedIndex(index);
//            rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
//        }
    }
}
