package it.unimib.gup.model.responses;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.model.HomePost;

public class GroupListResponse {
    private List<Group> groups;
    private boolean isError;
    private boolean isLoading;

    public List<Group> getGroups() {

        if (groups != null) {
            Collections.sort(groups, new Comparator<Group>() {
                public int compare(Group o1, Group o2) {
                    // compare two instance of `Score` and return `int` as result.
                    if (o2.getCreatedAt() < o1.getCreatedAt()) {
                        return -1;
                    } else if (o2.getCreatedAt() > o1.getCreatedAt()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

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
