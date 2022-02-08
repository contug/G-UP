package it.unimib.gup.model;

import java.util.Date;

public class Meeting {

    public enum MeetingType  {
        online,
        offline
    }

    private MeetingType type;
    private Date date;
    private String url;

    public Meeting() {
        // For JSON mapping
    }

    public Meeting(MeetingType type, Date date, String url) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
