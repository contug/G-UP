package it.unimib.gup.ui.main.group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Post;
import it.unimib.gup.repository.groups.GroupsRepository;
import it.unimib.gup.repository.groups.IGroupsRepository;

public class GroupViewModel extends AndroidViewModel {

    private final IGroupsRepository mGroupsRepository;
    private MutableLiveData<GroupsResponse> mGroupsResponseMutableLiveData;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupsRepository = new GroupsRepository(application);
        mGroupsResponseMutableLiveData = getGroups();
    }

    public MutableLiveData<GroupsResponse> getGroups() {
        return mGroupsRepository.fetchGroups();
    }

    public MutableLiveData<Group> getGroup(String id) {
        return mGroupsRepository.fetchGroup(id);
    }

    public Group addGroup(String name, String description, Category category) {
        return mGroupsRepository.addGroup(name, description, category);
    }

    public void addPost(String groupId, String text) {
        mGroupsRepository.addPost(groupId, text);
    }

}
