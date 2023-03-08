package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//a log of all bookings that have been added
public class BookingLog {

    //fields
    private List<Rental> bookings;
    private int position; //represents position (0, 1, 2...) in list of bookings
    private int bookingID; //bookingID = position + 1 (so its 1, 2, 3...)

    //creates list of bookings that is empty
    public BookingLog() {
        bookings = new ArrayList<>();
    }

    //add new rental to list of bookings
    //MODIFIES: bookings
    //EFFECTS: add rental to the end of bookings
    //         also retrieves position of rental in bookings and creates a bookingID
    public void addRental(Rental rental) {
        bookings.add(rental);
        position = bookings.indexOf(rental); //retrieves index of rental and this represents position in bookings list
        getBookingID(position);
    }

    //converts the list of bookings to a string with each booking numbered
    //MODIFIES: this
    //EFFECTS: builds a string with "Booking Log:" then numbers each entry of the list starting at 1,2,3...
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking Log: \n");
        int i = 0;
        for (Rental rental : bookings) {
            sb.append(i + 1 + ". " + rental.toString() + "\n");
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }

    //remove rental from list of bookings
    //REQUIRES: bookingID be in the list of bookings
    //MODIFIES: bookings
    //EFFECTS: retrieves position from bookingID and removes the rental from bookings at that position
    public void cancelRental(int bookingID) {
        position = bookingID - 1;
        bookings.remove(position);
    }

    //view rental from list of bookings
    //REQUIRES: bookingID be in the list of bookings
    //EFFECTS: retrieves booking based on bookingID and position and displays it for customer to verify
    public Rental viewRental(int bookingID) {
        position = bookingID - 1;
        return bookings.get(position);
    }

    //edit rental from list of bookings
    //REQUIRES: bookingID and rental is in the list of bookings
    //MODIFIES: this
    //EFFECTS: replaces the updated rental at the given bookingID
    public void editRental(int bookingID, Rental rental) {
        position = bookingID - 1;
        bookings.set(position, rental);
    }

    //get position
    //REQUIRES: rental is in list of bookings
    //EFFECTS: returns the index of rental from bookings list
    public int getPosition(Rental rental) {
        position = bookings.indexOf(rental);
        return position;
    }

    //from addRental method, retrieves position
    //MODIFIES: this
    //EFFECTS: sets bookingID to the position of rental + 1, so it goes 1,2,3...
    public int getBookingID(int position) {
        bookingID = position + 1;
        return bookingID;
    }

    //idea taken from JsonSerializationDemo
    //creates JSON representation of list of bookings called 'rentals'
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("rentals", rentalsToJson());
        return json;
    }

    //idea taken from JsonSerializationDemo
    //converts each rental in list of bookings to JSON representation
    //EFFECTS: returns things in this workroom as a JSON array
    private JSONArray rentalsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Rental rental : bookings) {
            jsonArray.put(rental.toJson());
        }
        return jsonArray;
    }


    //SIMPLE GETTERS

    //return list of all bookings
    public List<Rental> getAllBookings() {
        return bookings;
    }

    //return size of booking list
    public int getSize() {
        return bookings.size();
    }
}
