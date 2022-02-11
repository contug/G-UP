package it.unimib.gup.ui.main;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import it.unimib.gup.R;
import it.unimib.gup.adapter.DetailsGroupAdapter;
import it.unimib.gup.model.Group;

public class GroupDetailsFragment extends Fragment {

    private final static String TAG = "GroupDetailsFragment";

    private final String[] tabs = {"Posts", "Meetings"};

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_group_details, container, false);
        Group group = GroupDetailsFragmentArgs.fromBundle(getArguments()).getGroup();

        // --------- Prova di salvataggio sul database ------------
        final TextView textViewGroupName = view.findViewById(R.id.details_groups_group_name);
        final View viewGroupColor = view.findViewById(R.id.details_groups_group_circle);
        final FrameLayout frameLayoutCategoryContainer = view.findViewById(R.id.details_groups_category_container);
        final TextView textViewGroupCategory = view.findViewById(R.id.details_groups_category_text);
        final TextView textViewSubCount = view.findViewById(R.id.subscriber_count);
        final TextView textViewGroupDescription = view.findViewById(R.id.details_groups_description);

        textViewGroupName.setText(group.getName());
        viewGroupColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getColor())));
        frameLayoutCategoryContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getCategory().getColor())));
        textViewGroupCategory.setText(group.getCategory().getName());
        textViewSubCount.setText("0");
        textViewGroupDescription.setText(group.getDescription());

        final TabLayout tabLayout = view.findViewById(R.id.details_tab_layout);
        final ViewPager2 viewPager2 = view.findViewById(R.id.details_view_pager);
        DetailsGroupAdapter detailsGroupAdapter = new DetailsGroupAdapter(this, 2);
        viewPager2.setAdapter(detailsGroupAdapter);
        // ----------- Fino a qua

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();


        // Inflate the layout for this fragment
        return view;
    }


}