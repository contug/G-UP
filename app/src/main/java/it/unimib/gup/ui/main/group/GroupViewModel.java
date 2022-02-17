package it.unimib.gup.ui.main.group;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.Category;
import it.unimib.gup.repository.groups.GroupsRepository;
import it.unimib.gup.repository.groups.IGroupsRepository;

public class GroupViewModel extends AndroidViewModel {

    private final IGroupsRepository mGroupsRepository;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupsRepository = new GroupsRepository(application);
    }

    public void saveGroup(String name, String description, Category category) {
        mGroupsRepository.saveGroup(name, description, category);
    }




}
