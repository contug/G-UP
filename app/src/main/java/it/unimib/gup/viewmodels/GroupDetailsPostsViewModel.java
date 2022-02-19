package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.repository.groups.Repository;

public class GroupDetailsPostsViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupResponse> mGroupLiveData;

    public GroupDetailsPostsViewModel() {
        mRepository = new Repository();
    }

    public void fetchGroup(String groupId) {
        mGroupLiveData = mRepository.getGroupById(groupId);
        //isLoading.postValue(false);
    }
    public MutableLiveData<GroupResponse> getGroup(String groupId) {
        if(mGroupLiveData == null) {
            fetchGroup(groupId);
        }
        return mGroupLiveData;
    }
}
