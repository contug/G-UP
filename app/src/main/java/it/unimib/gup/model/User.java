package it.unimib.gup.model;

import java.util.HashMap;
import java.util.List;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private HashMap<String, String> groupSubscriptions;

    public User() {
        // For JSON mapping
    }

    public User(String id, String firstName, String lastName, String email, HashMap<String, String> groupSubscriptions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.groupSubscriptions = groupSubscriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getGroupSubscriptions() {
        return groupSubscriptions;
    }

    public void setGroupSubscriptions(HashMap<String, String> groupSubscriptions) {
        this.groupSubscriptions = groupSubscriptions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", groupSubscriptions=" + groupSubscriptions +
                '}';
    }
}
