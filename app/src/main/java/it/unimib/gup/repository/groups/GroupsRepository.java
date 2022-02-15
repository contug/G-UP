package it.unimib.gup.repository.groups;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.unimib.gup.model.Category;
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
    public void saveGroup(String id, String name, String description, Category category, String color) {

    }
}
