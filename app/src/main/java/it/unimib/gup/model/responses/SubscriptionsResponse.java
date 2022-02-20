package it.unimib.gup.model.responses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.model.HomePost;
import it.unimib.gup.model.Post;

public class SubscriptionsResponse {
    private List<Group> subscriptions;

    private boolean isError;
    private boolean isLoading;

    public SubscriptionsResponse() {
        this.subscriptions = new ArrayList<Group>();
    }

    public List<Group> getGroups() {
        return subscriptions;
    }

/*
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();

        for (Group tmpGroup : subscriptions) {
            List<Post> tmpPosts = new ArrayList<Post>(tmpGroup.getPosts().values());

            posts.addAll(tmpPosts);
        }

        return posts;
    }

 */

    public List<HomePost> getHomePosts() {
        List<HomePost> homePosts = new ArrayList<HomePost>();

        for (Group tmpGroup : subscriptions) {
            if (tmpGroup.getPosts() != null) {
                for (Post tmpPost : tmpGroup.getPosts().values()) {
                    HomePost tmpHomePost = new HomePost(
                            tmpGroup.getId(),
                            tmpGroup.getName(),
                            tmpGroup.getColor(),
                            tmpPost.getAuthor(),
                            tmpPost.getText(),
                            tmpPost.getData()
                    );

                    homePosts.add(tmpHomePost);
                }
            }
        }

        if (homePosts != null) {
            Collections.sort(homePosts, new Comparator<HomePost>() {
                public int compare(HomePost o1, HomePost o2) {
                    // compare two instance of `Score` and return `int` as result.
                    if (o2.getDate() < o1.getDate()) {
                        return -1;
                    } else if (o2.getDate() > o1.getDate()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        return homePosts;
    }

    public void addSubscription (Group group) {
        boolean isNew = true;

        int groupIdx = 0;
        for(Group tmpGroup : subscriptions) {
            if (tmpGroup.getId().equals(group.getId())) {
                subscriptions.set(groupIdx, group);
                isNew = false;
                break;
            }
            groupIdx++;
        }

        if(isNew) {
            this.subscriptions.add(group);
        }
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

