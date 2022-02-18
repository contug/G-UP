package it.unimib.gup.repository.groups;

import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;

public interface IGroupsRepository {

    Group saveGroup(String name, String description, Category category);

    MutableLiveData<GroupsResponse> fetchGroups();
}
