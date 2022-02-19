package it.unimib.gup.model.responses;

import java.util.List;

import it.unimib.gup.model.User;

public class UserListResponse {
    private List<User> users;
    private boolean isError;
    private boolean isLoading;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
