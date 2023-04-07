package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//a log of all rentals that have been added
public class BookingLog {

    //fields
    private List<Rental> rentals;
    private static List<Booking> bookingList = new ArrayList<>();
    private int position; //represents position (0, 1, 2...) in list of rentals
    private int bookingID; //bookingID = position + 1 (so its 1, 2, 3...)

    //CONSTRUCTOR: creates list of rentals that is empty
    public BookingLog() {
        rentals = new ArrayList<>();
    }

    //add new rental to list of rentals
    //MODIFIES: rentals
    //EFFECTS: add rental to the end of rentals (list)
    //         also retrieves position of rental in bookings and creates a bookingID
    public void addRental(Rental rental) {
        rentals.add(rental);
        position = rentals.indexOf(rental); //retrieves index of rental and this represents position in bookings list
        getBookingID(position);
    }

    //converts the list of rentals to a string with each booking numbered
    //MODIFIES: this
    //EFFECTS: builds a string with "Booking Log:" then numbers each entry of the list starting at 1,2,3...
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking Log: \n");
        int i = 0;
        for (Rental rental : rentals) {
            sb.append(i + 1 + ". " + rental.toString() + "\n");
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }

    //remove rental from list of rentals
    //REQUIRES: bookingID be in the list of bookings
    //MODIFIES: bookings
    //EFFECTS: retrieves position from bookingID and removes the rental from rentals at that position
    public void cancelRental(int bookingID) {
        position = bookingID - 1;
        rentals.remove(position);
    }

    //view rental from list of rentals
    //REQUIRES: bookingID be in the list of bookings
    //EFFECTS: retrieves rental based on bookingID and position and displays it for customer to verify
    public Rental viewRental(int bookingID) {
        position = bookingID - 1;
        return rentals.get(position);
    }

    //edit rental from list of rentals
    //REQUIRES: bookingID and rental is in the list of rentals
    //MODIFIES: this
    //EFFECTS: replaces the updated rental at the given bookingID
    public void editRental(int bookingID, Rental rental) {
        position = bookingID - 1;
        rentals.set(position, rental);
    }

    //get position
    //REQUIRES: rental is in list of rentals
    //EFFECTS: returns the index of rental from rentals list
    public int getPosition(Rental rental) {
        position = rentals.indexOf(rental);
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
    //creates JSON representation of list of rentals called 'rentals'
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
        for (Rental rental : rentals) {
            jsonArray.put(rental.toJson());
        }
        return jsonArray;
    }

    //given a booking, it will add to the bookingList in the GUI
    public static void addBookingToList(Booking booking) {
        bookingList.add(booking);
        EventLog.getInstance().logEvent(new Event("BOOKING ADDED."));
    }

    //given an index, it will remove from the bookingList in the GUI
    public static void removeBookingFromList(int index) {
        bookingList.remove(index);
        EventLog.getInstance().logEvent(new Event("BOOKING REMOVED."));
        //printLog(EventLog.getInstance());
    }

    //clears the entire bookingList
    public static void clearBookingList() {
        bookingList.clear();
        EventLog.getInstance().logEvent(new Event("ALL BOOKINGS CLEARED."));
    }

    public static void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }


    //SIMPLE GETTERS

    //return list of all rentals; see class-level comment in Rental/Booking class for more
    public List<Rental> getAllBookings() {
        return rentals;
    }

    //return list of all bookings from GUI; see class-level comment in Rental/Booking class for more
    public static List<Booking> getBookingList() {
        return bookingList;
    }

    //return size of booking list
    public int getSize() {
        return rentals.size();
    }
}
