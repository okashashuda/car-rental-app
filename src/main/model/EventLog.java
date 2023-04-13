package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
//copied directly from EventLog in AlarmSystem project, fixed to resolve checkstyle issues
//represents a log of alarm system events
public class EventLog implements Iterable<Event> {
    /** the only EventLog in the system (Singleton Design Pattern) */
    //fields
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    //CONSTRUCTOR: creates empty EventLog
    private EventLog() {
        events = new ArrayList<Event>();
    }

    /**
     * (Singleton Design Pattern)
     * @return  instance of EventLog
     */
    //get instance of EventLog, create one if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //add an event to the EventLog
    public void logEvent(Event e) {
        events.add(e);
    }

    //clear the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
