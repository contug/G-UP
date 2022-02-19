package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.repository.groups.Repository;

public class HomeViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<SubscriptionsResponse> mSubscriptions;

    public HomeViewModel() {
        mRepository = new Repository();
    }

    public void fetchSubscriptions() {
        mSubscriptions = mRepository.getSubscriptions();
    }

    public MutableLiveData<SubscriptionsResponse> getSubscriptions() {
        if(mSubscriptions == null) {
            fetchSubscriptions();
        }

        return mSubscriptions;
    }
}
