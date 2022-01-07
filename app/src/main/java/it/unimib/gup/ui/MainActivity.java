package it.unimib.gup.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.unimib.gup.R;
import it.unimib.gup.ui.main.CreateGroupActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Logic for the Bottom Navigation
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Use the toolbar as an actionbar for this activity
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.browse, R.id.account).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Listener for the items in the custom menu
        if (item.getItemId() == R.id.add_group) {
            Intent intent = new Intent(this, CreateGroupActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}