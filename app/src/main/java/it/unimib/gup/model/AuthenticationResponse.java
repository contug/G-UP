package it.unimib.gup.model;

/**
 * It represents a response used to manage the
 * Firebase Authentication response.
 */
public class AuthenticationResponse {

    private boolean success;
    private String message;

    public AuthenticationResponse() {

    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
