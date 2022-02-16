package it.unimib.gup.model;

import java.util.Calendar;
import java.util.Date;

public class Meeting {

    public enum MeetingType  {
        online,
        offline
    }

    private MeetingType type;
    private String date;
    private String url;

    public Meeting() {
        // For JSON mapping
    }

    public Meeting(MeetingType type, String date, String url) {
        this.type = type;
        this.date = date;
        this.url = url;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
