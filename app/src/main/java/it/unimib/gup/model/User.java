package it.unimib.gup.model;

public class User {

    private String name;
    private String uId;
    private String email;

    public User() {
    }

    public User(String name, String uId) {
        this.name = name;
    }

    public User(String name, String uId, String email) {
        this.name = name;
        this.uId = uId;
        this.email = email;
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
}
