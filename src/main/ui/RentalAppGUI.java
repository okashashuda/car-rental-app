package ui;

import model.EventLog;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static model.BookingLog.printLog;

//majority of the code for this phase was inspired by https://www.javatpoint.com/java-gridbaglayout
//the idea for the panel border was taken from https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
//GUI for a car rental application
public class RentalAppGUI extends JFrame {

    //fields
    protected JList<String> bookingLog;
    protected DefaultListModel listModel;

    protected JButton addButton;
    protected JButton cancelButton;
    protected JButton viewButton;
    protected JButton editButton;
    protected JButton saveButton;
    protected JButton loadButton;

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel ageLabel;
    private JLabel carLabel;
    private JLabel pickupDateLabel;
    private JLabel dropoffDateLabel;

    protected JTextField firstNameField;
    protected JTextField lastNameField;
    protected JTextField ageField;
    protected JComboBox carField;
    protected JTextField pickupDateField;
    protected JTextField dropoffDateField;

    //CONSTRUCTOR: creates the frame, all the panels, labels and buttons
    public RentalAppGUI() {
        //SETUP MAIN FRAME
        JFrame mainFrame = new JFrame("Rental Car App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.addWindowListener(printEventLogToConsole());

        //this is the individual list items (rentals) in the left panel (with booking log)
        getRentalList();

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

    //when GUI mainframe closing, this method executes, calls BookingLog.printLog method that prints EventLog to console
    //EFFECTS: when user closes the GUI mainframe, all events in EventLog get printed to console
    private WindowAdapter printEventLogToConsole() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        };
    }

    //INDIVIDUAL ITEMS IN BOOKING LIST
    private void getRentalList() {
        listModel = new DefaultListModel<>();
//        listModel.addElement("Rental 1");
//        listModel.addElement("Rental 2");
//        listModel.addElement("Rental 3");
    }

    //CUSTOMER INFO
    private JPanel getCustomerInfo() {
        setCustomerPanel(); //creates the labels and fields for the customer panel

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

    //creates the labels and fields for the panel that contains customer info
    private void setCustomerPanel() {
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        ageLabel = new JLabel("Age: (18+ to rent)");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(3);
    }

    //CAR INFO
    private JPanel getCarInfo() {
        setCarPanel(); //creates the labels and fields for the car panel

        //ADD MORE CARS HERE
        addCars();

        JPanel carInfo = new JPanel(new GridBagLayout());
        carInfo.setBorder(BorderFactory.createTitledBorder("Car Information"));

        GridBagConstraints carGrid = new GridBagConstraints();
        carGrid.anchor = GridBagConstraints.WEST;
        carGrid.insets = new Insets(5, 5, 5, 5);

        carGrid.gridx = 0;
        carGrid.gridy = 0;
        carInfo.add(carLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(carField, carGrid);

        carGrid.gridx = 0;
        carGrid.gridy = 1;
        carInfo.add(pickupDateLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(pickupDateField, carGrid);

        carGrid.gridx = 0;
        carGrid.gridy = 2;
        carInfo.add(dropoffDateLabel, carGrid);

        carGrid.gridx = 1;
        carInfo.add(dropoffDateField, carGrid);

        return carInfo;
    }

    //creates the labels and fields for the panel that contains car info
    private void setCarPanel() {
        carLabel = new JLabel("Car:");
        pickupDateLabel = new JLabel("Start Date: (yyyy-mm-dd)");
        dropoffDateLabel = new JLabel("End Date: (yyyy-mm-dd)");

        carField = new JComboBox<String>();
        pickupDateField = new JTextField(20);
        dropoffDateField = new JTextField(20);
    }

    //ADD CARS HERE
    private void addCars() {
        carField.addItem("2020 Honda Civic");
        carField.addItem("2022 Ford Explorer");
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

        ActionListener addButtonListener = new AddButtonListener(this, addButton);
        addButton.addActionListener(addButtonListener);

        ActionListener cancelButtonListener = new CancelButtonListener(this, cancelButton);
        cancelButton.addActionListener(cancelButtonListener);

        ActionListener saveButtonListener = new SaveButtonListener(this, saveButton);
        saveButton.addActionListener(saveButtonListener);

        ActionListener loadButtonListener = new LoadButtonListener(this, loadButton);
        loadButton.addActionListener(loadButtonListener);

        return buttonPanel;
    }
}