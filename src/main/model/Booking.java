package model;

import org.json.JSONObject;

import java.time.LocalDate;

//creates individual bookings based on the information entered into the GUI
public class Booking {

    //fields
    private String firstName;
    private String lastName;
    private int age;
    private String car;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;

    //CONSTRUCTOR: creates booking with first name, last name, age, car, pickup date and dropoff date from info in GUI
    public Booking(String firstName, String lastName, int age, String car, String pickupDate, String dropoffDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.car = car;
        this.pickupDate = LocalDate.parse(pickupDate);
        this.dropoffDate = LocalDate.parse(dropoffDate);
    }

    //creates JSON representation of each booking that is entered into the GUI
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("age", age);
        json.put("car", car);
        json.put("pickup", pickupDate.toString());
        json.put("dropoff", dropoffDate.toString());
        return json;
    }


    //SIMPLE GETTERS

    //returns first name from GUI field
    public String getFirstName() {
        return firstName;
    }

    //returns last name from GUI field
    public String getLastName() {
        return lastName;
    }

    //returns age from GUI field
    public int getAge() {
        return age;
    }

    //returns car from GUI field
    public String getCar() {
        return car;
    }

    //returns pickup date from GUI field
    public LocalDate getPickupDate() {
        return pickupDate;
    }

    //returns dropoff date from GUI field
    public LocalDate getDropoffDate() {
        return dropoffDate;
    }
}

