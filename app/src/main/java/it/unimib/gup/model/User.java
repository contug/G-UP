package it.unimib.gup.model;

import java.util.List;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> groupSubscriptions;

    public User() {
        // For JSON mapping
    }

    public User(String id, String firstName, String lastName, String email, List<String> groupSubscriptions) {
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

    public List<String> getGroupSubscriptions() {
        return groupSubscriptions;
    }

    public void setGroupSubscriptions(List<String> groupSubscriptions) {
        this.groupSubscriptions = groupSubscriptions;
    }
}
