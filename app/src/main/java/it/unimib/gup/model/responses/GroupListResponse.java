package it.unimib.gup.model.responses;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.model.Group;

public class GroupListResponse {
    private List<Group> groups;
    private boolean isError;
    private boolean isLoading;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
