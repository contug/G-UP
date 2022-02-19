package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.repository.groups.Repository;

public class HomeViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupListResponse> mGroupsLiveData;

    public HomeViewModel() {
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

    public void subscribe(String groupId) {
        mRepository.subscribe(groupId);
    }


}
