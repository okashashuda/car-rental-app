package model;

//represents a customer with full name and age
public class Customer {

    //constants
    public static final int MIN_AGE = 18;

    //fields
    private String firstName;
    private String lastName;
    private int age;

    public Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

    public boolean isValidRenter() {
        return age >= MIN_AGE;
    }
}
