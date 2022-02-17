package it.unimib.gup.repositories.BrowseGroups;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.gup.model.Group;

public interface IBrowseGroupsRepository {
    MutableLiveData<List<Group>> fetchGroups();
}
