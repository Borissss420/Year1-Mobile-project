package fi.Team4.timetrackerapp;

import java.util.ArrayList;
import java.util.List;

public class Events {

    public List<Event> events;
    private static final Events ourInstance = new Events();

    public static Events getInstance() {
        return ourInstance;
    }
    private Events() {
        events = new ArrayList<Event>();
        events.add(new Event("Boris' Birthday","Idle", "20-04-2002", "00:00", "00:00"));
    }
    public void addEvent(String eventName, String eventType, String date, String startTime, String endTime) {
        this.events.add(new Event(eventName, eventType, date, startTime, endTime));
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getEventIndex(Event event) {
        return this.events.indexOf(event);
    }

    public List<Event> deleteEvent(int eventIndex) {
        this.events.remove(eventIndex);
        return events;
    }
}
