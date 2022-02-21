package it.unimib.gup.model.responses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.model.Post;

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

    public List<Post> getPosts() {

        List<Post> tmpPosts = new ArrayList<Post>();

        if (group.getPosts() != null) {
            tmpPosts = new ArrayList<Post>(group.getPosts().values());
            tmpPosts.sort(new Comparator<Post>() {
                public int compare(Post o1, Post o2) {
                    return Long.compare(o2.getData(), o1.getData());
                }
            });
        }

        return tmpPosts;
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
