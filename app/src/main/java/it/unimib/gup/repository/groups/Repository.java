package it.unimib.gup.repository.groups;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.model.responses.UserListResponse;
import it.unimib.gup.model.responses.UserResponse;
import it.unimib.gup.utils.Constants;

public class Repository {

    private static final String TAG = "Repository";

    private final DatabaseReference mFirebaseDatabase;
    private final FirebaseAuth mAuth;

    public Repository() {

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
    }

    public MutableLiveData<GroupListResponse> getGroups() {
        MutableLiveData<GroupListResponse> responseLiveData = new MutableLiveData<GroupListResponse>();
        GroupListResponse response = new GroupListResponse();

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Group> groups = new ArrayList<>();

                for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                    Group group = snapshotChild.getValue(Group.class);
                    groups.add(group);
                }

                response.setGroups(groups);
                responseLiveData.postValue(response);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Error getting data", error.toException());
            }
        });

        return responseLiveData;
    }

    public MutableLiveData<GroupResponse> getGroupById(String groupId) {
        MutableLiveData<GroupResponse> responseLiveData = new MutableLiveData<GroupResponse>();
        GroupResponse response = new GroupResponse();

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                response.setGroup(snapshot.getValue(Group.class));
                responseLiveData.postValue(response);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return responseLiveData;
    }

    public UserListResponse getUsers() {
        UserListResponse response = new UserListResponse();

        mFirebaseDatabase.child(Constants.USER_COLLECTION).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> users = new ArrayList<>();

                for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                    User user = snapshotChild.getValue(User.class);
                    users.add(user);
                }

                response.setUsers(users);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Error getting data", error.toException());
            }
        });

        return response;
    }

    public UserResponse getUserById(String userId) {
        UserResponse response = new UserResponse();


        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                response.setUser(snapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return response;
    }

    public Group addGroup(String name, String description, Category category) {
        DatabaseReference groupsCollection = mFirebaseDatabase.child(Constants.GROUP_COLLECTION);

        DatabaseReference pushedGroup = groupsCollection.push();

        String ownerId = mAuth.getUid();

        HashMap<String, String> members = new HashMap<>();
        members.put(ownerId, ownerId);

        Group newGroup = new Group(pushedGroup.getKey(), ownerId, name, description, category, null, null, null);
        pushedGroup.setValue(newGroup);
        subscribe(pushedGroup.getKey());

        return newGroup;
    }

    public void subscribe(String groupId) {
        String memberId = mAuth.getUid();

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("members").child(memberId).
                setValue(memberId).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "onComplete: subscribed");
            }
        });

        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(memberId).child("subscriptions").child(groupId).setValue(groupId);
    }

    public void unsubscribe(String groupId) {
        String memberId = mAuth.getUid();

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("members").child(memberId).
                removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "onComplete: ciao");
            }
        });
    }

    public void deleteGroup(String groupId) {
        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).removeValue();
    }

    public void addPost(String groupId, String text) {

        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(mAuth.getUid()).get().
                addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DataSnapshot snapshot) {
                        User currentUser = snapshot.getValue(User.class);
                        if(!text.isEmpty()) {
                            Post post = new Post(currentUser.getFirstName() + " " + currentUser.getLastName(), text);
                            DatabaseReference pushedPost = mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).
                                    child(Constants.POST_COLLECTION).push();
                            post.setId(pushedPost.getKey());
                            pushedPost.setValue(post);
                        }
                    }
                });
    }

    public void addMeeting(String groupId, String type, String text, String date) {
        Meeting tmpMeeting = new Meeting(type, text, date);

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("meetings").child(type).setValue(tmpMeeting);

    }

    public MutableLiveData<SubscriptionsResponse> getSubscriptions() {
        String uId = mAuth.getUid();
        MutableLiveData<SubscriptionsResponse> responseLiveData = new MutableLiveData<>();
        SubscriptionsResponse response = new SubscriptionsResponse();
        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("subscriptions").
                addValueEventListener(new ValueEventListener() {
                    HashMap<String, String> subscriptions = new HashMap<>();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        subscriptions = snapshot.getValue(HashMap.class);
                        Log.d("@@@", "subscriptions" + subscriptions.toString());
                        for(String tmp : subscriptions.keySet()) {
                            mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(tmp).
                                    addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Group group = snapshot.getValue(Group.class);
                                            response.addSubscription(tmp, group);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                        }
                        responseLiveData.postValue(response);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return responseLiveData;
    }
}
