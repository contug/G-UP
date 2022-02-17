package it.unimib.gup.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BrowseGroupsViewModelFactory implements ViewModelProvider.Factory {
    private String userId;

    public BrowseGroupsViewModelFactory(String userId) {
        this.userId = userId;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BrowseGroupsViewModel(userId);
    }
}
