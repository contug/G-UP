package it.unimib.gup.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import it.unimib.gup.R;
import it.unimib.gup.ui.authentication.LoginFragment;
import it.unimib.gup.ui.authentication.RegisterFragment;

public class AuthenticationAdapter extends FragmentStateAdapter {

    private final int totalTabs;

    public AuthenticationAdapter(@NonNull FragmentActivity fragmentActivity, int totalTabs) {
        super(fragmentActivity);
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
