package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.repository.groups.Repository;

public class CreatePostViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupListResponse> mGroupsLiveData;

    public CreatePostViewModel() {
        mRepository = new Repository();
    }
    public void addPost(String groupId, String text) {
        mRepository.addPost(groupId, text);
    }
}
