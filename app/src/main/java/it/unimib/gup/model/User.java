package it.unimib.gup.model;

import java.util.List;

public class User {

    private String name;
    private String surname;
    private String uId;
    private String email;
    private List<Category> preferences;

    public User() {
    }

    public List<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Category> preferences) {
        this.preferences = preferences;
    }

    public User(String name, String uId) {
        this.name = name;
    }

    public User(String name, String surname, String uId, String email) {
        this.name = name;
        this.surname = surname;
        this.uId = uId;
        this.email = email;
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

    public String getuId() {
        return uId;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", uId='" + uId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
