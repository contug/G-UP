package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.User;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.model.responses.UserResponse;
import it.unimib.gup.repository.groups.Repository;

public class AccountViewModel extends ViewModel {
    private final Repository mRepository;
    private UserResponse mUser;
    private MutableLiveData<SubscriptionsResponse> mSubscriptionsLiveData;

    public AccountViewModel() {
        mRepository = new Repository();
        mUser = mRepository.getUserById();
    }

    public void fetchGroups() {
        mSubscriptionsLiveData = mRepository.getSubscriptions();
    }

    public MutableLiveData<SubscriptionsResponse> getSubscriptions() {
        if(mSubscriptionsLiveData == null) {
            fetchGroups();
        }

        return mSubscriptionsLiveData;
    }

    public void editUser(String newFirstName, String newLastName, String newEmail) {
        User tmpUser = mUser.getUser();
        mRepository.editUser(tmpUser.getFirstName(), tmpUser.getLastName(), newFirstName, newLastName, newEmail);
    }
}
