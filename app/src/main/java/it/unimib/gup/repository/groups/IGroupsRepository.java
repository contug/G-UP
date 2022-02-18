package it.unimib.gup.repository.groups;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;

public interface IGroupsRepository {

    Group addGroup(String name, String description, Category category);
    void removeGroup(String id);

    void addPost(String groupId, Post post);
    void removePost(String id);


    MutableLiveData<GroupsResponse> fetchGroups();
    MutableLiveData<Group> fetchGroup(String id);

    MutableLiveData<List<User>> fetchUsers();
    MutableLiveData<User> fetchUser(String id);
}
