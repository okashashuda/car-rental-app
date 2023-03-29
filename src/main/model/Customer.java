package model;

//represents a customer with full name and age
public class Customer {

    //constants
    public static final int MIN_AGE = 18;

    //fields
    private String firstName;
    private String lastName;
    private int age;

    //CONSTRUCTOR: creates customer with first name, last name and age
    public Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    //checks if customer can rent based on age
    //EFFECTS: produces TRUE if the person is of at least minimum age to rent, FALSE otherwise.
    public boolean isValidRenter() {
        return age >= MIN_AGE;
    }


    //SIMPLE GETTERS

    //returns customer's first name
    public String getFirstName() {
        return firstName;
    }

    //returns customer's last name
    public String getLastName() {
        return lastName;
    }

    //returns customer's age
    public int getAge() {
        return age;
    }
}
