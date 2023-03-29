package ui;

import model.BookingLog;
import model.Car;
import model.Customer;
import model.Rental;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

//console-based ui for a car rental application
public class RentalApp {

    private static final String DESTINATION = "./data/bookinglog.json";
    private LocalDate pickup;
    private LocalDate dropoff;
    private Rental updatedRental;
    private BookingLog bookingLog = new BookingLog();
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //CONSTRUCTOR: runs the console-based UI
    public RentalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(DESTINATION);
        jsonReader = new JsonReader(DESTINATION);
        runApp();
    }

    //idea taken from TellerApp program
    //MODIFIES: this
    //EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String userInput;

        while (keepGoing) {
            displayMenu();
            userInput = input.next().toLowerCase();

            if (userInput.equals("q")) {
                keepGoing = false;
            } else {
                continueProcess(userInput);
            }
        }
        System.out.println("Thank you for using the application!");
    }

    //idea taken from TellerApp program
    //EFFECTS: displays menu to user
    private void displayMenu() {
        System.out.println("Please select from the following options: (enter letter)\n");
        System.out.println("a -> add booking");
        System.out.println("c -> cancel booking");
        System.out.println("v -> view booking details");
        System.out.println("e -> edit booking details");
        System.out.println("s -> save booking to file");
        System.out.println("l -> load booking from file");
        System.out.println("q -> quit\n");
    }

    //EFFECTS: processes user input
    private void continueProcess(String userInput) {
        if (userInput.equals("a")) {
            addBooking();
        } else if (userInput.equals("c")) {
            cancelBooking();
        } else if (userInput.equals("v")) {
            viewBooking();
        } else if (userInput.equals("e")) {
            editBooking();
        } else if (userInput.equals("s")) {
            saveBooking();
        } else if (userInput.equals("l")) {
            loadBooking();
        } else {
            System.out.println("Your input was INVALID.");
        }
    }

    //creates booking to add to list
    //EFFECTS: takes user input to create customer, select a car, create rental, and calculate total cost
    private void addBooking() {
        System.out.print("Enter first name: ");
        String firstName = input.next();

        System.out.print("Enter last name: ");
        String lastName = input.next();

        System.out.print("Enter age: ");
        int age = input.nextInt();

        Customer customer = new Customer(firstName, lastName, age);

        if (!customer.isValidRenter()) {
            System.out.println("You're too young to rent a car. Try again.\n");
        } else {
            Car selectedCar = chooseCar();

            Rental rental = new Rental(customer, selectedCar, pickup, dropoff);
            bookingLog.addRental(rental);

            System.out.println("Your rental period is " + rental.getNumOfDays(pickup, dropoff) + " day(s) from "
                    + pickup + " to " + dropoff);
            System.out.println("Your subtotal for this rental is: $" + rental.totalCost() + " at $"
                    + Rental.COST_PER_DAY + "/day\n");
            System.out.println("BOOKING SUCCESSFULLY ADDED!\n");
            System.out.println(bookingLog);
        }
    }

    //helper function for addBooking that asks user to choose car for the rental
    //EFFECTS: user chooses one car, selects pickup and dropoff date and program confirms user input
    private Car chooseCar() {
        Car c1 = new Car("Honda", "Civic", 2020);
        Car c2 = new Car("Ford", "Explorer", 2022);

        System.out.println("\n\nSelect a car to rent: (enter number) \n");
        System.out.println("1. " + c1.getMake() + " " + c1.getModel() + " " + "(" + c1.getYear() + ")");
        System.out.println("2. " + c2.getMake() + " " + c2.getModel() + " " + "(" + c2.getYear() + ")");
        int choice = input.nextInt();

        System.out.println("Enter pickup date: (in YYYY-MM-DD format)");
        pickup = LocalDate.parse(input.next());
        System.out.println("Enter dropoff date: (in YYYY-MM-DD format)");
        dropoff = LocalDate.parse(input.next());

        Car selectedCar;
        if (choice == 1) {
            System.out.println("\nYou chose: " + c1.getMake() + " " + c1.getModel() + " " + "(" + c1.getYear() + ")");
            selectedCar = c1;
        } else if (choice == 2) {
            System.out.println("\nYou chose: " + c2.getMake() + " " + c2.getModel() + " " + "(" + c2.getYear() + ")");
            selectedCar = c2;
        } else {
            selectedCar = null;
            System.out.println("You have not chosen a car.");
        }
        return selectedCar;
    }

    //cancels bookings by removing from list
    //EFFECTS: user chooses an existing booking to cancel, by entering the bookingID
    private void cancelBooking() {
        if (bookingLog.getSize() == 0) {
            System.out.println("There's no bookings to cancel.\n");
        } else {
            System.out.println(bookingLog);
            System.out.print("Enter Booking ID to remove: ");
            int bookingID = input.nextInt();
            bookingLog.cancelRental(bookingID);
            System.out.println("\nBOOKING SUCCESSFULLY REMOVED!\n");

            System.out.println(bookingLog);
        }
    }

    //view the details of the booking from list
    //EFFECTS: user chooses an existing booking to view, by entering the bookingID
    private void viewBooking() {
        if (bookingLog.getSize() == 0) {
            System.out.println("There's no bookings to view.\n");
        } else {
            System.out.println(bookingLog);
            System.out.print("Enter Booking ID to view: ");
            int bookingID = input.nextInt();
            System.out.println("\n" + bookingLog.viewRental(bookingID) + "\n");
        }
    }

    //edit the details of a booking from list
    //EFFECTS: user chooses an existing booking to edit, and updates it in the list
    private void editBooking() {
        if (bookingLog.getSize() == 0) {
            System.out.println("There's no bookings to edit.\n");
        } else {
            System.out.println(bookingLog);
            System.out.print("Enter Booking ID to edit: ");
            int bookingID = input.nextInt();

            System.out.println("\nEnter updated details below: ");
            updateBooking();

            bookingLog.editRental(bookingID, updatedRental);

            System.out.println("\nYour new rental is " + updatedRental.getNumOfDays(pickup, dropoff) + " day(s) from "
                    + pickup + " to " + dropoff);
            System.out.println("Your subtotal for this new rental is: $" + updatedRental.totalCost() + " at $"
                    + Rental.COST_PER_DAY + "/day\n");
            System.out.println("BOOKING UPDATED!\n");
            System.out.println(bookingLog);
        }
    }

    //helper function for editBooking that asks user to enter the details to edit the booking
    //EFFECTS: user chooses to change the details of the booking
    private void updateBooking() {
        System.out.print("Enter first name: ");
        String firstName = input.next();

        System.out.print("Enter last name: ");
        String lastName = input.next();

        System.out.print("Enter age: ");
        int age = input.nextInt();

        Customer customer = new Customer(firstName, lastName, age);

        if (!customer.isValidRenter()) {
            System.out.println("You're too young to rent a car. Try again.\n");
        } else {
            Car selectedCar = chooseCar();

            updatedRental = new Rental(customer, selectedCar, pickup, dropoff);
        }
    }

    //idea taken from JsonSerializationDemo
    //EFFECTS: user chooses to save their booking to file
    private void saveBooking() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookingLog);
            jsonWriter.close();
            System.out.println("SAVED TO: " + DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("CANNOT WRITE TO FILE: " + DESTINATION + "\n");
        }

    }

    //idea taken from JsonSerializationDemo
    //EFFECTS: user chooses to load their booking from file
    private void loadBooking() {
        try {
            bookingLog = jsonReader.read();
            System.out.println("LOADED FROM: " + DESTINATION);
        } catch (IOException e) {
            System.out.println("CANNOT READ FROM FILE: " + DESTINATION + "\n");
        }

    }
}
