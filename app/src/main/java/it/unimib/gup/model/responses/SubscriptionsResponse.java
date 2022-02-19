package it.unimib.gup.model.responses;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public List<Post>  getPosts() {
        List<Post> posts = new ArrayList<Post>();

        for (Group tmpGroup : subscriptions) {
            List<Post> tmpPosts = new ArrayList<Post>(tmpGroup.getPosts().values());

            posts.addAll(tmpPosts);
        }

        return posts;
    }

                        /*

    public HomePost(String groupId, String groupName, String groupColor, String author, String text, Date date) {
                        this.groupId = groupId;
                        this.groupName = groupName;
                        this.groupColor = groupColor;
                        this.author = author;
                        this.text = text;
                        this.date = date;
                    }
                     */

    public List<HomePost> getHomePosts() {
        List<HomePost> homePosts = new ArrayList<HomePost>();

        for (Group tmpGroup : subscriptions) {
            for (Post tmpPost : tmpGroup.getPosts().values()) {

                HomePost tmpHomePost = new HomePost(
                        tmpGroup.getId(),
                        tmpGroup.getName(),
                        tmpGroup.getColor(),
                        tmpPost.getAuthor(),
                        tmpPost.getText(),
                        new Date());

                homePosts.add(tmpHomePost);
            }

        }

        return homePosts;
    }

    /*
    public void setSubscriptions(HashMap<String, Group> subscriptions) {
        this.subscriptions = subscriptions;
    }



     */

    public void addSubscription (Group group) {
        this.subscriptions.add(group);

        /*

        Log.d("@@@", "Group: " + group.toString());
        Log.d("@@@", "Subscriptions: " + subscriptions.toString());
         */
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
