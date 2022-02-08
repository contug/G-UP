package it.unimib.gup.model;

import java.util.List;

public class Group {

    private String id;
    private String name;
    private String description;
    private Category category;
    private List<String> members;
    private List<Meeting> meetings;
    private List<Note> notes;
    private String color;

    public Group() {
        // For JSON mapping
    }

    public Group(String id, String name, String description, Category category, List<String> members, List<Meeting> meetings, List<Note> notes, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.members = members;
        this.meetings = meetings;
        this.notes = notes;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
