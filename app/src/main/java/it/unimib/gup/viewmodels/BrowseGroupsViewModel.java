package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.repository.groups.Repository;

public class BrowseGroupsViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupListResponse> mGroupsLiveData;

    public BrowseGroupsViewModel() {
        mRepository = new Repository();
    }

    public void fetchGroups() {
        mGroupsLiveData = mRepository.getGroups();
    }

    public MutableLiveData<GroupListResponse> getGroups() {
        if(mGroupsLiveData == null) {
            fetchGroups();
        }
        return mGroupsLiveData;
    }


}