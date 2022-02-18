package it.unimib.gup.ui.main.group;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.repository.groups.GroupsRepository;
import it.unimib.gup.repository.groups.IGroupsRepository;

public class GroupViewModel extends AndroidViewModel {

    private final IGroupsRepository mGroupsRepository;
    private MutableLiveData<GroupsResponse> mGroupsResponseMutableLiveData;
    private MutableLiveData<List<User>> mUsersResponseMutableLiveData;
    private String mCurrentGroupId;


    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupsRepository = new GroupsRepository(application);
        mGroupsResponseMutableLiveData = getGroups();
        mUsersResponseMutableLiveData = getUsers();
    }

    public MutableLiveData<GroupsResponse> getGroups() {
        return mGroupsRepository.fetchGroups();
    }

    public MutableLiveData<Group> getGroup(String id) {
        return mGroupsRepository.fetchGroup(id);
    }

    public MutableLiveData<List<User>> getUsers() {
        return mGroupsRepository.fetchUsers();
    }

    public User getUser(String id) {

        List<User> tmpUsers = mUsersResponseMutableLiveData.getValue();
        User user = new User();

        for (User tmpUser : tmpUsers ) {
            if(tmpUser.getId().equals(id)) {
                user = tmpUser;
                break;
            }
        }

        return user;
    }

    public List<Post> getAllPosts(String id) {
        User currentUser = getUser(id);

        List<Post> posts = new ArrayList<Post>();
        for (Group group : mGroupsResponseMutableLiveData.getValue().getGroups()) {
            //if(currentUser.getGroupSubscriptions().contains(group.getId())) {

                List<Post> tmpGroups = new ArrayList<>(group.getPosts().values());
                posts.addAll(tmpGroups);
            //}

        }

        return posts;
    }

    public Group addGroup(String name, String description, Category category) {
        return mGroupsRepository.addGroup(name, description, category);
    }

    public void addPost(String groupId, Post post) {
        mGroupsRepository.addPost(groupId, post);
    }

    public void setCurrentGroupId(String id) {
        mCurrentGroupId = id;
    }

    public String getCurrentGroupId() {
        return mCurrentGroupId;
    }
}
