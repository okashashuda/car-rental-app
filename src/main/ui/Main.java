package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            new RentalApp();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }
    }
}
