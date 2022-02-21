package it.unimib.gup.model.responses;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.model.HomePost;

public class GroupListResponse {
    private List<Group> groups;
    private boolean isError;
    private boolean isLoading;

    public List<Group> getGroups() {

        if (groups != null) {
            groups.sort(new Comparator<Group>() {
                public int compare(Group o1, Group o2) {
                    return Long.compare(o2.getCreatedAt(), o1.getCreatedAt());
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
