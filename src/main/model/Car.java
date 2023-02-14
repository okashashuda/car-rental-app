package model;

// represents car with make, model, and year
public class Car {

    //fields
    private String make;
    private String model;
    private int year;
    private boolean available;

    public Car(String make, String model, int year, boolean available) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.available = available;
    }

    //if car has been rented, set availability to false
    //else if still available, set availability to true
    //MODIFIES: this
    //EFFECTS: changes setAvailable if car is no longer available to rent
    public void setAvailable(boolean available) {
        this.available = available;
    }


    //SIMPLE GETTERS

    //returns car's make
    public String getMake() {
        return make;
    }

    //returns car's model
    public String getModel() {
        return model;
    }

    //returns car's year
    public int getYear() {
        return year;
    }

    //returns current availability of car
    public boolean isAvailable() {
        return available;
    }
}
