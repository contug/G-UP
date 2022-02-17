package it.unimib.gup.repository.groups;

import android.app.Application;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.utils.Constants;

public class GroupsRepository implements IGroupsRepository {

    private static final String TAG = "GroupsRepository";

    private final DatabaseReference mFirebaseDatabase;

    private final DatabaseReference mGroupsCollection;




    public GroupsRepository() {
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        mGroupsCollection = mFirebaseDatabase.child("groups");
    }


    @Override
    public void addGroup(String name, String description, String categoryId) {
        DatabaseReference reference = mGroupsCollection.push();
        Log.d("TEST", reference.toString());
    }
}
