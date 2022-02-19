package it.unimib.gup.model.responses;

import java.util.List;

import it.unimib.gup.model.Group;

public class GroupResponse {
    private Group group;
    private boolean isError;
    private boolean isLoading;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
