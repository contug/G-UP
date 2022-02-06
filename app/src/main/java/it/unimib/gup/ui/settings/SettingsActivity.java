package it.unimib.gup.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import it.unimib.gup.R;
import it.unimib.gup.adapter.SettingCategoriesAdapter;

public class SettingsActivity extends AppCompatActivity {
    private final String TAG = "SETTINGS_ACTIVITY";

    private CategoryItems[] categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        categoryItems = new CategoryItems[6];

        categoryItems[0] = new CategoryItems("Humanities", "#00CFB9");
        categoryItems[1] = new CategoryItems("Natural-sciences", "#FE4C6A");
        categoryItems[2] = new CategoryItems("Formal-sciences", "#FECB70");
        categoryItems[3] = new CategoryItems("Professions", "#433CA2");
        categoryItems[4] = new CategoryItems("Social-sciences", "#20569A");
        categoryItems[5] = new CategoryItems("Other", "#58CCEE");

        GridView mCategoriesItemsGridView = findViewById(R.id.fos_selection_grid);

        SettingCategoriesAdapter adapter = new SettingCategoriesAdapter(this, android.R.layout.simple_list_item_1, categoryItems);

        mCategoriesItemsGridView.setAdapter(adapter);
    }
}