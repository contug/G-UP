package it.unimib.gup.model;

import java.util.List;

public class User {

    private String name;
    private String surname;
    private String userId;
    private String email;
    private List<String> groupsAdminId;
    private List<String> groupsPartOfId;
    private List<String> userPreferences;

    public User() {
        // For JSON mapping
    }

    public User(String name, String userId) {
        this.name = name;
    }

    public User(String name, String surname, String userId, String email) {
        this.name = name;
        this.surname = surname;
        this.userId = userId;
        this.email = email;
    }

    public List<String> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<String> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public List<String> getGroupsAdminId() {
        return groupsAdminId;
    }

    public void setGroupsAdminId(List<String> groupsAdminId) {
        this.groupsAdminId = groupsAdminId;
    }

    public List<String> getGroupsPartOfId() {
        return groupsPartOfId;
    }

    public void setGroupsPartOfId(List<String> groupsPartOfId) {
        this.groupsPartOfId = groupsPartOfId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", userPreferences=" + userPreferences +
                '}';
    }
}
