package it.unimib.gup.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import it.unimib.gup.ui.main.group.MeetingsFragment;
import it.unimib.gup.ui.main.group.PostsFragment;

public class DetailsGroupAdapter extends FragmentStateAdapter {

    private int totalTabs;

    public DetailsGroupAdapter(@NonNull Fragment fragment, int totalTabs) {
        super(fragment);
        this.totalTabs  = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PostsFragment();
            case 1:
                return new MeetingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
