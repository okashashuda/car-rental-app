package model;

import java.util.Calendar;
import java.util.Date;

//copied directly from Event in AlarmSystem project, fixed to resolve checkstyle issues
//represents an Event that happens when the program is run, gets recorded with a description and exact time it happened
public class Event {

    //constants
    private static final int HASH_CONSTANT = 13;

    //fields
    private Date dateLogged;
    private String description;

    /**
     * @param description  a description of the event
     */
    //CONSTRUCTOR: creates event with a description and the current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * @return  the date of the event
     */
    //get the date of this event (including exact time)
    public Date getDate() {
        return dateLogged;
    }

    /**
     * @return  the description of the event
     */
    //get the description of this event
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}



