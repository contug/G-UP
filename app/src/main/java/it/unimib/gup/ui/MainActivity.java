package it.unimib.gup.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.unimib.gup.R;

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

        // Use the toolbar as an action bar for this activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.browse, R.id.account).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);




    }
}