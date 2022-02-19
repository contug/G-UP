package it.unimib.gup.model.responses;

import java.util.List;

import it.unimib.gup.model.User;

public class UserResponse {
    private User user;
    private boolean isError;
    private boolean isLoading;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
