package it.unimib.gup.repository.groups;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.utils.Constants;

public class GroupsRepository implements IGroupsRepository {

    private static final String TAG = "GroupsRepository";

    private final FirebaseAuth mAuth;
    private final DatabaseReference mFirebaseDatabase;


    private final Application mApplication;

    public GroupsRepository(Application application) {
        this.mApplication = application;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
    }

    @Override
    public void saveGroup(String name, String description, Category category) {

        DatabaseReference groupsCollection = mFirebaseDatabase.child(Constants.GROUP_COLLECTION);
        DatabaseReference pushedGroup = groupsCollection.push();




        // Sistemare con id utenti, meetings, posts
        List<String> tmpUsers = Arrays.asList("id_user_2", "id_user_1");

        Group newGroup = new Group(pushedGroup.getKey(), name, description, category, tmpUsers, null, null);

        pushedGroup.setValue(newGroup);




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
