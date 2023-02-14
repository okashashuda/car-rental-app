package ui;

import model.BookingLog;
import model.Car;
import model.Customer;
import model.Rental;

import java.time.LocalDate;
import java.util.Scanner;

public class RentalApp {

    private Customer customer;
    private Car c1;
    private Car c2;
    private LocalDate pickup;
    private LocalDate dropoff;
    private BookingLog bookingLog = new BookingLog();
    private Scanner input;

    public RentalApp() {
        input = new Scanner(System.in);
        runApp();
    }

    //idea taken from TellerApp program
    //MODIFIES: this
    //EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String userInput = null;

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
        System.out.println("Please select from the following options:\n");
        System.out.println("a -> add booking");
        System.out.println("c -> cancel booking");
        System.out.println("v -> view booking details");
        System.out.println("e -> edit booking details");
        System.out.println("q -> quit\n");
    }

    private void continueProcess(String userInput) {
        if (userInput.equals("a")) {
            addBooking();
        } else if (userInput.equals("c")) {
            cancelBooking();
        } else if (userInput.equals("v")) {
            viewBooking();
        } else if (userInput.equals("e")) {
            editBooking();
        } else {
            System.out.println("Invalid input");
        }
    }

    private void addBooking() {
        System.out.print("Enter first name: ");
        String firstName = input.next();

        System.out.print("Enter last name: ");
        String lastName = input.next();

        System.out.print("Enter age: ");
        Integer age = input.nextInt();

        Car selectedCar = chooseCar();

        Customer customer = new Customer(firstName, lastName, age);
        Rental rental = new Rental(customer, selectedCar, pickup, dropoff);
        bookingLog.addRental(rental);

        System.out.println(bookingLog);
    }

    private Car chooseCar() {
        Car c1 = new Car("Honda", "Civic", 2020, true);
        Car c2 = new Car("Ford", "Explorer", 2022, true);

        System.out.println("\n\nSelect a car to rent: \n");
        System.out.println("1. " + c1.getMake() + " " + c1.getModel() + " " + "(" + c1.getYear() + ")");
        System.out.println("2. " + c2.getMake() + " " + c2.getModel() + " " + "(" + c2.getYear() + ")");
        Integer choice = input.nextInt();

        System.out.println("Enter pickup date: (in YYYY-MM-DD format)");
        pickup = LocalDate.parse(input.next());
        System.out.println("Enter dropoff date: (in YYYY-MM-DD format)");
        dropoff = LocalDate.parse(input.next());

        Car selectedCar;
        if (choice == 1) {
            System.out.println("You chose: " + c1.getMake() + " " + c1.getModel() + " " + "(" + c1.getYear() + ")");
            selectedCar = c1;
        } else if (choice == 2) {
            System.out.println("You chose: " + c2.getMake() + " " + c2.getModel() + " " + "(" + c2.getYear() + ")");
            selectedCar = c2;
        } else {
            selectedCar = null;
            System.out.println("You have not chosen a car.");
        }
        System.out.println("Your rental period is from " + pickup + " to " + dropoff + "\n");
        return selectedCar;
    }

    private void cancelBooking() {
        System.out.println("cancel");
    }

    private void viewBooking() {
        System.out.println("view");
    }

    private void editBooking() {
        System.out.println("edit");
    }
}
