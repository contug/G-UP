package it.unimib.gup.model.responses;

import java.util.HashMap;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.model.Post;

public class SubscriptionsResponse {
    private List<Post> posts;

    private HashMap<String, Group> subscriptions;

    private boolean isError;
    private boolean isLoading;

    public List<Post> getPosts() {
        return posts;
    }

    public void setSubscriptions(HashMap<String, Group> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription (String key, Group group) {
        this.subscriptions.put(key, group);
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
