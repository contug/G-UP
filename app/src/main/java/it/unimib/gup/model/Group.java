package it.unimib.gup.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String groupId;
    private String name;
    private String subCategory;
    private List<String> groupMembersId;
    private List<Event> events;

    public Group() {
        // For JSON mapping
    }

    public Group(String name, String subCategory, List<String> groupMembersId, ArrayList<Event> events) {
        this.name = name;
        this.subCategory = subCategory;
        this.groupMembersId = groupMembersId;
        this.events = events;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public List<String> getGroupMembersId() {
        return groupMembersId;
    }

    public void setGroupMembersId(List<String> groupMembersId) {
        this.groupMembersId = groupMembersId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
