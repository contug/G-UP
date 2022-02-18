package it.unimib.gup.model;

import java.util.List;

public class GroupsResponse {
    private List<Group> groups;
    private boolean isError;
    private boolean isLoading;

    public List<Group> getGroups() {
        return groups;
    }

    public Group getGroup(String id) {
        Group tmpGroup = new Group();
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                tmpGroup = group;
                break;
            }
        }

        return tmpGroup;
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
