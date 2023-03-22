package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;

public class RentalAppGUI extends JFrame {

    private JList<String> bookingLog;
    private DefaultListModel listModel;
    private JButton addButton;
    private JButton cancelButton;
    private JButton viewButton;
    private JButton editButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel ageLabel;
    private JLabel carLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JComboBox carField;
    private JTextField startDateField;
    private JTextField endDateField;

    public RentalAppGUI() {
        //SETUP MAIN FRAME
        JFrame mainFrame = new JFrame("Rental Car App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        //mainFrame.setLocationRelativeTo(null); //DELETE LATER, IF NEEDED

        listModel = new DefaultListModel<>();
        listModel.addElement("Rental 1");
        listModel.addElement("Rental 2");
        listModel.addElement("Rental 3");

        //SETUP LEFT PANEL (with booking log)
        bookingLog = new JList<String>(listModel);
        bookingLog.setFixedCellWidth(mainFrame.getWidth() / 3);
        JScrollPane bookingLogScrollPane = new JScrollPane(bookingLog);
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Booking Log"));
        leftPanel.add(bookingLogScrollPane, BorderLayout.CENTER);
        mainFrame.add(leftPanel, BorderLayout.WEST);

        //CUSTOMER INFO
        JPanel customerInfo = getCustomerInfo();

        //CAR INFO
        JPanel carInfo = getCarInfo();

        //BUTTONS
        JPanel buttonPanel = getButtonPanel();

        //SETUP RIGHT PANEL (with customer info, car info, and buttons)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(customerInfo, BorderLayout.NORTH);
        rightPanel.add(carInfo, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(rightPanel, BorderLayout.CENTER);

        //DISPLAY GUI
        mainFrame.setVisible(true);
    }

    //BUTTONS
    private JPanel getButtonPanel() {
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        HireListener hireListener = new HireListener(addButton);
        addButton.setActionCommand("Add");
        addButton.addActionListener(hireListener);
        addButton.setEnabled(false);

        return buttonPanel;
    }

    //CUSTOMER INFO
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private JPanel getCustomerInfo() {
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        ageLabel = new JLabel("Age: (18+ to rent)");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(3);

        JPanel customerInfo = new JPanel(new GridBagLayout());
        customerInfo.setBorder(BorderFactory.createTitledBorder("Customer Information"));

        GridBagConstraints customerGrid = new GridBagConstraints();
        customerGrid.anchor = GridBagConstraints.WEST;
        customerGrid.insets = new Insets(5, 5, 5, 5);

        customerGrid.gridx = 0;
        customerGrid.gridy = 0;
        customerInfo.add(firstNameLabel, customerGrid);

        customerGrid.gridx = 1;
        customerInfo.add(firstNameField, customerGrid);

        customerGrid.gridx = 0;
        customerGrid.gridy = 1;
        customerInfo.add(lastNameLabel, customerGrid);

        customerGrid.gridx = 1;
        customerInfo.add(lastNameField, customerGrid);

        customerGrid.gridx = 2;
        customerGrid.gridy = 1;
        customerInfo.add(ageLabel, customerGrid);

        customerGrid.gridx = 3;
        customerGrid.gridy = 1;
        customerInfo.add(ageField, customerGrid);

        return customerInfo;
    }

    //CAR INFO
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private JPanel getCarInfo() {
        carLabel = new JLabel("Car:");
        startDateLabel = new JLabel("Start Date: (yyyy-mm-dd)");
        endDateLabel = new JLabel("End Date: (yyyy-mm-dd)");

        carField = new JComboBox<String>();
        startDateField = new JTextField(20);
        endDateField = new JTextField(20);

        //ADD MORE CARS HERE
        carField.addItem("2020 Honda Civic");
        carField.addItem("2022 Ford Explorer");

        JPanel carInfo = new JPanel(new GridBagLayout());
        carInfo.setBorder(BorderFactory.createTitledBorder("Car Information"));

        GridBagConstraints carGrid = new GridBagConstraints();
        carGrid.anchor = GridBagConstraints.WEST;
        carGrid.insets = new Insets(5, 5, 5, 5);

        carGrid.gridx = 0;
        carGrid.gridy = 0;
        carInfo.add(carLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(carField,carGrid);

        carGrid.gridx = 0;
        carGrid.gridy = 1;
        carInfo.add(startDateLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(startDateField, carGrid);

        carGrid.gridx = 0;
        carGrid.gridy = 2;
        carInfo.add(endDateLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(endDateField, carGrid);

        return carInfo;
    }



    //This listener is shared by the text field and the hire button.
    class HireListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public HireListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = firstNameField.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                firstNameField.requestFocusInWindow();
                firstNameField.selectAll();
                return;
            }

            int index = bookingLog.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(firstNameField.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            firstNameField.requestFocusInWindow();
            firstNameField.setText("");

            //Select the new item and make it visible.
            bookingLog.setSelectedIndex(index);
            bookingLog.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (bookingLog.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                cancelButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                cancelButton.setEnabled(true);
            }
        }
    }
}
