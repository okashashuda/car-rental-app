package ui;

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
                || rentalAppGUI.ageField.getText().isEmpty() || rentalAppGUI.startDateField.getText().isEmpty()
                || rentalAppGUI.endDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields");
        } else {
            rentalAppGUI.listModel.addElement(rentalAppGUI.firstNameField.getText() + " "
                    + rentalAppGUI.lastNameField.getText());
            rentalAppGUI.firstNameField.setText("");
            rentalAppGUI.lastNameField.setText("");
            rentalAppGUI.ageField.setText("");
            rentalAppGUI.startDateField.setText("");
            rentalAppGUI.endDateField.setText("");
            rentalAppGUI.bookingLog.setSelectedIndex(index);
            rentalAppGUI.bookingLog.ensureIndexIsVisible(index);
        }
    }
}
