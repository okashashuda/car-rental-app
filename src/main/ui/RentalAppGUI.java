package ui;

import java.awt.*;
import javax.swing.*;

public class RentalAppGUI extends JFrame {

    private JList<String> rentalList;
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
        // Set up the main frame
        JFrame mainFrame = new JFrame("Rental Car App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.setLocationRelativeTo(null);

        // Set up the left panel with the rental list
        rentalList = new JList<String>();
        JScrollPane rentalListScrollPane = new JScrollPane(rentalList);
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Rental Bookings"));
        leftPanel.add(rentalListScrollPane, BorderLayout.CENTER);
        mainFrame.add(leftPanel, BorderLayout.WEST);

        //CUSTOMER INFO
        JPanel customerInfo = getCustomerInfo();

        //CAR INFO
        JPanel carInfo = getCarInfo();

        //BUTTONS
        JPanel buttonPanel = getButtonPanel();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(customerInfo, BorderLayout.NORTH);
        rightPanel.add(carInfo, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(rightPanel, BorderLayout.CENTER);

        // Display the GUI
        mainFrame.setVisible(true);
    }

    //BUTTONS
    private JPanel getButtonPanel() {
        // Set up the right panel with the buttons and input fields
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        customerInfo.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        customerInfo.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        customerInfo.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        customerInfo.add(lastNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        customerInfo.add(ageLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        customerInfo.add(ageField, gbc);
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

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.insets = new Insets(5, 5, 5, 5);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        carInfo.add(carLabel, gbc2);

        gbc2.gridx = 1;
        carInfo.add(carField,gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        carInfo.add(startDateLabel, gbc2);

        gbc2.gridx = 1;
        carInfo.add(startDateField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        carInfo.add(endDateLabel, gbc2);

        gbc2.gridx = 1;
        carInfo.add(endDateField, gbc2);
        return carInfo;
    }
}
