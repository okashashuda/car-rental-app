package model;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//represents rental with number of days, pickup and drop-off location
public class Rental {

    //constants
    public static final int COST_PER_DAY = 30; //in dollars

    //fields
    private Customer customer;
    private Car car;
    private LocalDate pickup;
    private LocalDate dropoff;
    private double totalCost;
    private int numOfDays;

    public Rental(Customer customer, Car car, LocalDate pickup, LocalDate dropoff) {
        this.customer = customer;
        this.car = car;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

    //calculate total cost of rental
    //MODIFIES: this
    //EFFECTS: calculates total cost of rental by multiplying cost per day by the number of days car is being rented
    public double totalCost() {
        getNumOfDays(pickup, dropoff);
        totalCost = numOfDays * COST_PER_DAY;
        return totalCost;
    }

    //https://stackabuse.com/how-to-get-the-number-of-days-between-dates-in-java/ used for reference
    //return number of days car is being rented
    //MODIFIES: this
    //EFFECTS: calculates the number of days between pickup and dropoff (will be used to calculate total cost)
    public int getNumOfDays(LocalDate pickup, LocalDate dropoff) {
        numOfDays = (int) ChronoUnit.DAYS.between(pickup, dropoff);
        return numOfDays += 1;
    }

    //https://www.baeldung.com/java-string-to-date used for reference
    //return date of pickup in YYYY-MM-DD format
    //EFFECTS: parses the string input of date and converts to a Date format
    public LocalDate getPickup() {
        pickup = LocalDate.parse("2023-03-20");
        return pickup;
    }

    //https://www.baeldung.com/java-string-to-date used for reference
    //return date of dropoff in YYYY-MM-DD format
    //EFFECTS: parses the string input of date and converts to a Date format
    public LocalDate getDropoff() {
        dropoff = LocalDate.parse("2023-03-24");
        return dropoff;
    }

    //builds a string with all info of the rental
    //EFFECTS: creates a string with all information of the rental
    public String toString() {
        return "Rental: \n -Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n"
                + " -Car: " + car.getMake() + " " + car.getModel() + "\n"
                + " -Pickup: " + pickup + "\n"
                + " -Dropoff: " + dropoff;
    }

    //EFFECTS: creates a JSON representation of all information of the rental
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", customer.getFirstName());
        json.put("lastName", customer.getLastName());
        json.put("age", customer.getAge());
        json.put("make", car.getMake());
        json.put("model", car.getModel());
        json.put("year", car.getYear());
        json.put("pickup", pickup.toString());
        json.put("dropoff", dropoff.toString());
        return json;
    }


    //SIMPLE GETTERS

    //get customer details
    public Customer getCustomerInfo() {
        return customer;
    }

    //get car details
    public Car getCarInfo() {
        return car;
    }
}
