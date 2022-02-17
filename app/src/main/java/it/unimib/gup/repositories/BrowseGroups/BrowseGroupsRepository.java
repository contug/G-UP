package it.unimib.gup.repositories.BrowseGroups;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;
import it.unimib.gup.utils.Constants;


public class BrowseGroupsRepository implements IBrowseGroupsRepository {

    private final DatabaseReference mFirebaseDatabase;
    private MutableLiveData<List<Group>> mGroupsLiveData;

    public BrowseGroupsRepository(String userId) {
        this.mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        this.mGroupsLiveData = new MutableLiveData<>(new ArrayList<>());
    }

    @Override
    public MutableLiveData<List<Group>> fetchGroups() {

        DatabaseReference mGroupsCollection = mFirebaseDatabase.child("groups");

        Log.d("@@@", "fetchGroups");

        mGroupsCollection.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Group> tmpGroups = new ArrayList<>();

                for (DataSnapshot snapshotChild : snapshot.getChildren()) {
                    String key = snapshotChild.getKey();
                    String name = snapshotChild.child("name").getValue().toString();
                    String description = snapshotChild.child("description").getValue().toString();

                        tmpGroups.add(
                                new Group(
                                        key,
                                        name,
                                        description,
                                        new Category("Formal Sciences", "#F43F5E"),
                                        Arrays.asList("user_id_1", "user_id_2", "user_id_3"),
                                        Arrays.asList(new Meeting(Meeting.MeetingType.offline, new Date(), "maps"), new Meeting(Meeting.MeetingType.offline, new Date(), "maps")),
                                        Arrays.asList(new Post("note_id_1", "user_id_1", "Post text 1"), new Post("note_id_1", "user_id_1", "Post text 1")),
                                        "#38a9ff")
                        );
                    }

                mGroupsLiveData.postValue(tmpGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mGroupsLiveData;
    }
}