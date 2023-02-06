package model;

import java.time.LocalDate;

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

    public Rental(Customer customer, Car car, LocalDate pickup, LocalDate dropoff) {
        this.customer = customer;
        this.car = car;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

    //return date of pickup in YYYY-MM-DD format
    public LocalDate getPickup() {
        pickup = LocalDate.parse("2023-03-20");
        return pickup;
    }

    //return date of dropoff in YYYY-MM-DD
    public LocalDate getDropoff() {
        dropoff = LocalDate.parse("2023-03-24");
        return dropoff;
    }


    public double totalCost() {
        return 0; //stub
    }

}
