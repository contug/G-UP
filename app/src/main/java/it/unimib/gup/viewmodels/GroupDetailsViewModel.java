package it.unimib.gup.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.repository.groups.Repository;

public class GroupDetailsViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupResponse> mGroupLiveData;

    public GroupDetailsViewModel() {
        mRepository = new Repository();
    }

    public void fetchGroup(String groupId) {
        mGroupLiveData = mRepository.getGroupById(groupId);
    }

    public MutableLiveData<GroupResponse> getGroup(String groupId) {
        fetchGroup(groupId);
        return mGroupLiveData;
    }

    public MutableLiveData<GroupResponse> getGroupForPosts() {
        return mGroupLiveData;
    }

    public String getAuthorName(Post post) {
        Log.d("@@@", "id -> " + post.getAuthor());
        User user  = mRepository.getUserById(post.getAuthor()).getUser();

        return user.getFirstName() + " " + user.getLastName();
    }
}
