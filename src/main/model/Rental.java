package model;

//represents rental with number of days, pickup and drop-off location
public class Rental {

    //constants
    public static final int COST_PER_DAY = 30;

    //fields
    private String pickup;
    private String dropoff;
    private int numOfDays;

    public Rental(String pickup, String dropoff, int numOfDays) {
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.numOfDays = numOfDays;
    }

    //return pickup location of rental car
    public String getPickup() {
        return pickup;
    }

    //return dropoff location of rental car
    public String getDropoff() {
        return dropoff;
    }

    //return number of days the rental is
    public int getNumOfDays() {
        return numOfDays;
    }


}
