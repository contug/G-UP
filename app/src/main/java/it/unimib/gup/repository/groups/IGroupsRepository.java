package it.unimib.gup.repository.groups;

import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Post;

public interface IGroupsRepository {

    Group saveGroup(String name, String description, Category category);
    void savePost(String groupId, Post post);

    MutableLiveData<GroupsResponse> fetchGroups();
}
