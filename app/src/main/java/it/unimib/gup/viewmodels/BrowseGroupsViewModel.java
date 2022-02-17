package it.unimib.gup.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.model.Group;
import it.unimib.gup.repositories.BrowseGroups.BrowseGroupsRepository;

public class BrowseGroupsViewModel extends ViewModel {
    private MutableLiveData<List<Group>> mGroups;
    private final MutableLiveData<String> mSearchValue;
    private final BrowseGroupsRepository mBrowseGroupsRepository;

    public BrowseGroupsViewModel(String userId) {
        mBrowseGroupsRepository = new BrowseGroupsRepository(userId);
        mGroups = new MutableLiveData<List<Group>>(new ArrayList<>());
        mSearchValue = new MutableLiveData<String>("");
    }

    public LiveData<List<Group>> getGroups() {
        return fetchGroups();
    }

    public MutableLiveData<List<Group>> fetchGroups(){
        return mBrowseGroupsRepository.fetchGroups();
    }

    public void setSearchValue(String newSearchValue) {
        mSearchValue.setValue(newSearchValue);
        getGroups();
    }
}
