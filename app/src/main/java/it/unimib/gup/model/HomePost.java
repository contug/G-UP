package it.unimib.gup.model;

import java.util.Date;

public class HomePost {
    private String groupId;
    private String groupName;
    private String groupColor;
    private String author;
    private String text;
    private String date;

    public HomePost(String groupId, String groupName, String groupColor, String author, String text, String date) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupColor = groupColor;
        this.author = author;
        this.text = text;
        this.date = date;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(String groupColor) {
        this.groupColor = groupColor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HomePost{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupColor='" + groupColor + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}