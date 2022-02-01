package it.unimib.gup.model;

/**
 * It represents a response used to manage the
 * Firebase Authentication response.
 */
public class AuthenticationResponse {

    private boolean succes;
    private String message;

    public AuthenticationResponse() {

    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucces() {
        return succes;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "succes=" + succes +
                ", message='" + message + '\'' +
                '}';
    }
}
