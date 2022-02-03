package it.unimib.gup.model;

import java.util.List;

public class Category {

    private String name;
    private List<Group> groups;
    private int numGroups;

    public Category(String name, List<Group> groups) {
        this.name = name;
        this.groups = groups;
        this.numGroups = 0;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public void setNumGroups(int numGroups) {
        this.numGroups = numGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
