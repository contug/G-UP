package it.unimib.gup.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import it.unimib.gup.R;
import it.unimib.gup.adapter.SettingsFieldOfStudyAdapter;

public class SettingsActivity extends AppCompatActivity {
    private final String TAG = "SETTINGS_ACTIVITY";

    private FieldOfStudy[] fieldOfStudies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fieldOfStudies = new FieldOfStudy[4];

        fieldOfStudies[0] = new FieldOfStudy("Biology", "#666666");
        fieldOfStudies[1] = new FieldOfStudy("Filosophy", "#333333");
        fieldOfStudies[2] = new FieldOfStudy("Computer Science", "#666666");
        fieldOfStudies[3] = new FieldOfStudy("Other", "#333333");


        GridView mFieldOfStudyGridView = findViewById(R.id.fos_selection_grid);

        SettingsFieldOfStudyAdapter adapter = new SettingsFieldOfStudyAdapter(this, android.R.layout.simple_list_item_1, fieldOfStudies);

        mFieldOfStudyGridView.setAdapter(adapter);
    }
}