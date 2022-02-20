package it.unimib.gup.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.repository.groups.Repository;

public class GroupDetailsViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupResponse> mGroupLiveData;
    private final String currentUserId;

    public GroupDetailsViewModel() {
        Log.d("TAG", "GroupDetailsViewModel: ");
        mRepository = new Repository();
        currentUserId = mRepository.getCurrentUserId();
    }

    public void fetchGroup(String groupId) {
        mGroupLiveData = mRepository.getGroupById(groupId);
    }

    public MutableLiveData<GroupResponse> getGroup(String groupId) {
        fetchGroup(groupId);
        return mGroupLiveData;
    }

    public void leaveGroup(String groupId) {
        mRepository.leaveGroup(groupId);
    }

    public MutableLiveData<GroupResponse> getGroupNoFetch() {
        return mGroupLiveData;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

}
