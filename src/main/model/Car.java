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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = true;
    }
}
