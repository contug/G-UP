package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.repository.groups.Repository;

public class CreateGroupViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupListResponse> mGroupsLiveData;

    public CreateGroupViewModel() {
        mRepository = new Repository();
    }
    public Group addGroup(String name, String description, Category category) {
        return mRepository.addGroup(name, description, category);
    }
}
