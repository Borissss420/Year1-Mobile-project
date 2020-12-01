package fi.Team4.timetrackerapp;

public class Events {

    private String eventName;
    private String eventType;
    private String date;
    private int startTime;
    private int endTime;

    public Events(String eventName, String eventType, String date, int startTime, int endTime) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Event name: " + this.eventName +
                ", event type: " + this.eventType +
                ", date: " + this.date +
                ", start time: " + this.startTime +
                ", end time: " + this.endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
