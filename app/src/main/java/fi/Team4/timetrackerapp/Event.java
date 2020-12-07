package fi.Team4.timetrackerapp;

public class Event {

    private String eventName;
    private String eventType;
    private String date;
    private String startTime;
    private String endTime;

    public Event(String eventName, String eventType, String date, String startTime, String endTime) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Name: " + this.eventName +
                ",   Type: " + this.eventType +
                "\nDate: " + this.date +
                "\nStart at: " + this.startTime +
                ",   End at: " + this.endTime + "\n";
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
