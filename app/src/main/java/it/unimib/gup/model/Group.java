package it.unimib.gup.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private Category category;
    private String subCategory;
    private ArrayList<User> groupMembers;
    private ArrayList<Event> events;

    public Group(String name, Category category, String subCategory, ArrayList<User> groupMembers, ArrayList<Event> events) {
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.groupMembers = groupMembers;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public ArrayList<User> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(ArrayList<User> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
