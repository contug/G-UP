package it.unimib.gup.repository.groups;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.utils.Constants;

public class GroupsRepository implements IGroupsRepository {

    private static final String TAG = "GroupsRepository";

    private final FirebaseAuth mAuth;
    private final DatabaseReference mFirebaseDatabase;
    private final MutableLiveData<GroupsResponse> mGroupsResponseMutableLiveData;
    private final MutableLiveData<Group> mGroupResponseMutableLiveData;
    private final MutableLiveData<List<User>> mUsersResponseMutableLiveData;

    private final Application mApplication;

    public GroupsRepository(Application application) {
        this.mApplication = application;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        mGroupsResponseMutableLiveData = new MutableLiveData<>();
        mGroupResponseMutableLiveData = new MutableLiveData<>();
        mUsersResponseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<GroupsResponse> fetchGroups() {

        Log.d(TAG, "fetchGroups: ");
        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).addValueEventListener(new ValueEventListener() {

            GroupsResponse groupsResponse = new GroupsResponse();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onComplete: ");
                groupsResponse.setError(false);
                List<Group> groups = new ArrayList<>();

                for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                    Group group = snapshotChild.getValue(Group.class);
                    groups.add(group);
                }

                groupsResponse.setGroups(groups);
                mGroupsResponseMutableLiveData.postValue(groupsResponse);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Error getting data", error.toException());
                groupsResponse.setError(true);
            }
        });

        return mGroupsResponseMutableLiveData;
    }

    public MutableLiveData<Group> fetchGroup(String id) {

        mGroupResponseMutableLiveData.postValue(mGroupsResponseMutableLiveData.getValue().getGroup(id));

        return  mGroupResponseMutableLiveData;
    }

    @Override
    public MutableLiveData<List<User>> fetchUsers() {
        List<User> tmpUsers = new ArrayList<>();

        mFirebaseDatabase.child(Constants.USER_COLLECTION).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                    User user = snapshotChild.getValue(User.class);
                    tmpUsers.add(user);
                }
                mUsersResponseMutableLiveData.postValue(tmpUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Error getting data", error.toException());
            }
        });

        return mUsersResponseMutableLiveData;
    }

    @Override
    public MutableLiveData<User> fetchUser(String id) {
        return null;
    }


    @Override
    public Group addGroup(String name, String description, Category category) {
        DatabaseReference groupsCollection = mFirebaseDatabase.child(Constants.GROUP_COLLECTION);

        DatabaseReference pushedGroup = groupsCollection.push();

        Group newGroup = new Group(pushedGroup.getKey(), name, description, category, null, null, null);

        pushedGroup.setValue(newGroup);

        return newGroup;
    }

    @Override
    public void removeGroup(String id) {

    }

    public void addPost(String groupId, Post post) {
        DatabaseReference pushedPost = mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).
                child(Constants.POST_COLLECTION).push();
        post.setId(pushedPost.getKey());
        pushedPost.setValue(post);
    }

    @Override
    public void removePost(String id) {

    }







    /*    public void saveGroup(Group group) {
            pushedGroup.child("id").setValue(pushedGroup.child(id).getKey());
        pushedGroup.child("name").setValue(name);
        pushedGroup.child("description").setValue(description);
        pushedGroup.child("category").setValue(category);
        pushedGroup.child("color").setValue(color);
        pushedGroup.child(Constants.MEETING_COLLECTION).setValue(null);
        pushedGroup.child(Constants.POST_COLLECTION).setValue(null);
    }
        public void saveGroup(String id, String name, String description, Category category, String color) {
        DatabaseReference pushedGroup = mFirebaseDatabase.child(Constants.GROUP_COLLECTION).push();

        pushedGroup.child("id").setValue(pushedGroup.child(id).getKey());
        pushedGroup.child("name").setValue(name);
        pushedGroup.child("description").setValue(description);
        pushedGroup.child("category").setValue(category);
        pushedGroup.child("color").setValue(color);
        pushedGroup.child(Constants.MEETING_COLLECTION).setValue(null);
        pushedGroup.child(Constants.POST_COLLECTION).setValue(null);
    }


    public void saveMeeting(String groupId, Meeting meeting) {
        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child(Constants.MEETING_COLLECTION).push().setValue(meeting);
    }

    public void savePost(String groupId, Post post){
        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child(Constants.POST_COLLECTION)
                .push().setValue(post);
    }
    */
}
