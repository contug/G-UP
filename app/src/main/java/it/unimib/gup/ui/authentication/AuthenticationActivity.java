package it.unimib.gup.ui.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import it.unimib.gup.R;
import it.unimib.gup.adapter.LoginAdapter;

/**
 * Activity to manage the login and registration of a user.
 */
public class AuthenticationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    FloatingActionButton google, fb, twitter;
    float opacityValue = 0;

    private String[] tabs = {"Sign In", "Sign Up"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        google = findViewById(R.id.fab_google);
        fb = findViewById(R.id.fab_facebook);
        twitter = findViewById(R.id.fab_twitter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LoginAdapter loginAdapter = new LoginAdapter(this, 2);
        viewPager.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();

        google.setTranslationY(300);
        fb.setTranslationY(300);
        twitter.setTranslationY(300);

        google.setAlpha(opacityValue);
        fb.setAlpha(opacityValue);
        twitter.setAlpha(opacityValue);


        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

    }


}