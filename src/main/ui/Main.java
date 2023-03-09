package ui;

import java.io.FileNotFoundException;

public class Main {

    //idea taken from JsonSerializationDemo
    public static void main(String[] args) throws FileNotFoundException {
        try {
            new RentalApp();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }
    }
}
