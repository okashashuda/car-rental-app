package ui;

import java.io.FileNotFoundException;

public class Main {

    //idea taken from JsonSerializationDemo
    public static void main(String[] args) throws FileNotFoundException {
        //new RentalApp(); //uncomment this for console-based UI and comment the line right below this
        new RentalAppGUI(); //uncomment this for GUI and comment the line right above this

//        try {
//            new RentalApp();
//            new RentalAppGUI();
//        } catch (FileNotFoundException e) {
//            System.out.println("FILE NOT FOUND");
//        }
    }
}
