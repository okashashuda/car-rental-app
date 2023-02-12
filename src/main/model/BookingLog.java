package model;

import java.util.ArrayList;
import java.util.List;

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
    public void addRental(Rental rental) {
        bookings.add(rental);
        position = bookings.indexOf(rental); //retrieves index of rental and this represents position in bookings list
    }

    //remove rental from list of bookings
    public void cancelRental(int bookingID) {
        bookings.remove(bookingID);
    }

    //view rental from list of bookings
    public Rental viewRental(int bookingID) {
        return bookings.get(bookingID);
    }

    //edit rental from list of bookings
    public void editRental(int bookingID, Rental rental) {
        bookings.set(bookingID, rental);
    }

    //get list of all bookings
    public List<Rental> getAllBookings() {
        return bookings;
    }

    //size of booking list
    public int size() {
        return bookings.size();
    }

    //get position
    public int getPosition(Rental rental) {
        position = bookings.indexOf(rental);
        return position;
    }

    //from addRental method, retrieves position
    public int getBookingID(int position) {
        bookingID = position + 1;
        return bookingID;
    }

}
