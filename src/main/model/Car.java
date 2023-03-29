package model;

//represents car with make, model, and year
public class Car {

    //fields
    private String make;
    private String model;
    private int year;

    //CONSTRUCTOR: creates a car with make, model and year
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
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
}
