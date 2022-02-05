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

        fieldOfStudies = new FieldOfStudy[6];

        fieldOfStudies[0] = new FieldOfStudy("Biology", "#22c55e");
        fieldOfStudies[1] = new FieldOfStudy("Filosophy", "#eab308");
        fieldOfStudies[2] = new FieldOfStudy("Computer Science", "#06b6d4");
        fieldOfStudies[3] = new FieldOfStudy("Literature", "#3b82f6");
        fieldOfStudies[4] = new FieldOfStudy("Physics", "#f97316");
        fieldOfStudies[5] = new FieldOfStudy("Other", "#64748b");

        GridView mFieldOfStudyGridView = findViewById(R.id.fos_selection_grid);

        SettingsFieldOfStudyAdapter adapter = new SettingsFieldOfStudyAdapter(this, android.R.layout.simple_list_item_1, fieldOfStudies);

        mFieldOfStudyGridView.setAdapter(adapter);
    }
}