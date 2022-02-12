package it.unimib.gup.ui.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.unimib.gup.R;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.utils.Constants;

public class CreateGroupActivity extends AppCompatActivity {

    public static final String TAG = "CREATE_GROUP_ACTIVITY";

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();

        Toolbar toolbar = findViewById(R.id.toolbar_create_group);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        // Check actionBar != null
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateGroupActivity.super.onBackPressed();
            }
        });

        final Button buttonCreateGroup = findViewById(R.id.button_create_group);
        final EditText editTextNameGroup = findViewById(R.id.create_group_name_edit_text);
        final Spinner spinnerCategoryGroup = findViewById(R.id.create_group_category_spinner);
        final EditText descriptionGroup = findViewById(R.id.create_group_description_edit_text);

        spinnerCategoryGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    TextView textView = (TextView)view;
                    textView.setTextColor(Color.GRAY);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // --------- Prova salvataggio di un gruppo sul database
        Group group = new Group();
        Category category = new Category();
        group.setId("groupID1");

        buttonCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setName(editTextNameGroup.getText().toString());
                category.setName(spinnerCategoryGroup.getSelectedItem().toString());
                group.setCategory(category);
                group.setDescription(descriptionGroup.getText().toString());
                mFirebaseDatabase.child("groups").child(group.getId()).setValue(group);
                CreateGroupActivity.super.onBackPressed();
            }
        });


    }
}