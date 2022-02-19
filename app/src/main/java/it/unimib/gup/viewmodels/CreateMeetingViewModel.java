package it.unimib.gup.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.repository.groups.Repository;

public class CreateMeetingViewModel extends ViewModel {

    private final Repository mRepository;
    private MutableLiveData<GroupListResponse> mGroupsLiveData;

    public CreateMeetingViewModel() {
        mRepository = new Repository();
    }
    public void addMeeting(String groupId, String type, String text, String date) {
        mRepository.addMeeting(groupId,type, date, text);
    }
}
