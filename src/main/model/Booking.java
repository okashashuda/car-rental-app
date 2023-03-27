package model;

import org.json.JSONObject;

import java.time.LocalDate;

public class Booking {

    private String firstName;
    private String lastName;
    private int age;
    private String car;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;

    public Booking(String firstName, String lastName, int age, String car, String pickupDate, String dropoffDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.car = car;
        this.pickupDate = LocalDate.parse(pickupDate);
        this.dropoffDate = LocalDate.parse(dropoffDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCar() {
        return car;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

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

//    public static Booking fromJson(JSONObject json) {
//        String firstName = json.getString("firstName");
//        String lastName = json.getString("lastName");
//        int age = json.getInt("age");
//        String car = json.getString("car");
//        String pickupDate = json.getString("pickup");
//        String dropoffDate = json.getString("dropoff");
//        return new Booking(firstName, lastName, age, car, pickupDate, dropoffDate);
//    }
}

