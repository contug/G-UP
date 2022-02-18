package it.unimib.gup.ui.main.group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.repository.groups.GroupsRepository;
import it.unimib.gup.repository.groups.IGroupsRepository;

public class GroupViewModel extends AndroidViewModel {

    private final IGroupsRepository mGroupsRepository;
    private MutableLiveData<GroupsResponse> mGroupsResponseMutableLiveData;

    private boolean fetchedGroupsFirstTime;


    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupsRepository = new GroupsRepository(application);
        fetchedGroupsFirstTime = false;
    }

    public MutableLiveData<GroupsResponse> getGroups() {
        if (!fetchedGroupsFirstTime) {
            mGroupsResponseMutableLiveData = mGroupsRepository.fetchGroups();
            fetchedGroupsFirstTime = true;
        }
        return mGroupsResponseMutableLiveData;
    }

    public Group saveGroup(String name, String description, Category category) {
        return mGroupsRepository.saveGroup(name, description, category);
    }




}
