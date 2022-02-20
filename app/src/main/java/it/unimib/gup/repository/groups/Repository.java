package it.unimib.gup.repository.groups;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.model.responses.UnsplashResponse;
import it.unimib.gup.model.responses.UserListResponse;
import it.unimib.gup.model.responses.UserResponse;
import it.unimib.gup.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public UserResponse getUserById() {
        String userId = mAuth.getUid();
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

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Unsplash unsplash = retrofit.create(Unsplash.class);

        Call<List<UnsplashResponse>> randomImageCall =  unsplash.randomImage("6QGh7CkiCS64jtYidL66AxgpoPi16B0eqiLCqasVLB4");

        UnsplashResponse response = new UnsplashResponse();
        try {
             response = randomImageCall.execute().body().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Group newGroup = new Group(pushedGroup.getKey(), ownerId, name, description, response.getRegularImage(), category, members, null, null);
        pushedGroup.setValue(newGroup);
        subscribe(pushedGroup.getKey());

        return newGroup;
    }

    public void subscribe(String groupId) {
        String memberId = mAuth.getUid();

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("members").child(memberId).
                setValue(memberId);

        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(memberId).child("subscriptions").child(groupId).setValue(groupId);
    }

    public void unsubscribe(String groupId) {
        String memberId = mAuth.getUid();

        String uId = mAuth.getUid();
        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("subscriptions").child(groupId).removeValue();
        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("members").child(uId).removeValue();
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
                            Post post = new Post(currentUser.getFirstName() + " " + currentUser.getLastName(), text, new Date().getTime());
                            DatabaseReference pushedPost = mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).
                                    child(Constants.POST_COLLECTION).push();
                            post.setId(pushedPost.getKey());
                            pushedPost.setValue(post);
                        }
                    }
                });
    }

    public void addMeeting(String groupId, String type, String date, String url) {
        Meeting tmpMeeting = new Meeting(type, date, url);

        mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).child("meetings").child(type).setValue(tmpMeeting);
    }

    public MutableLiveData<SubscriptionsResponse> getSubscriptions() {
        String uId = mAuth.getUid();
        MutableLiveData<SubscriptionsResponse> responseLiveData = new MutableLiveData<>();
        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("subscriptions").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SubscriptionsResponse response = new SubscriptionsResponse();
                        for(DataSnapshot snapshotChild : snapshot.getChildren()) {
                            String groupId = snapshotChild.getKey();
                            mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).
                                    addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshotGroup) {
                                            Group group = snapshotGroup.getValue(Group.class);
                                            response.addSubscription(group);
                                            responseLiveData.postValue(response);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return responseLiveData;
    }

    public void editUser(String oldFirstName, String oldLastName, String newFirstName, String newLastName, String newEmail) {
        String uId = mAuth.getUid();

        String mNewFirstName;
        if(!newFirstName.trim().isEmpty()) {
            mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("firstName").setValue(newFirstName);
            mNewFirstName = newFirstName;
        } else {
            mNewFirstName = oldFirstName;
        }

        String mNewLastName;
        if(!newLastName.trim().isEmpty()) {
            mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("lastName").setValue(newLastName);
            mNewLastName = newLastName;
        } else {
            mNewLastName = oldLastName;
        }

        if(!newEmail.trim().isEmpty()) {
            mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("email").setValue(newEmail);
            mAuth.getCurrentUser().updateEmail(newEmail);
        }

        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).child("subscriptions").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshotChild : snapshot.getChildren()) {
                            String groupId = snapshotChild.getKey();
                            mFirebaseDatabase.child(Constants.GROUP_COLLECTION).child(groupId).
                                    addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshotGroup) {
                                            Group group = snapshotGroup.getValue(Group.class);
                                            for(String key: group.getPosts().keySet()) {
                                                if(group.getPosts().get(key).getAuthor().toLowerCase()
                                                        .equals((oldFirstName + " " + oldLastName).toLowerCase())){
                                                    mFirebaseDatabase
                                                            .child(Constants.GROUP_COLLECTION)
                                                            .child(groupId)
                                                            .child("posts")
                                                            .child(key)
                                                            .child("author")
                                                            .setValue(mNewFirstName + " " + mNewLastName);
                                                }
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

    }

    public String getCurrentUserId() {
        return mAuth.getUid();
    }
}
