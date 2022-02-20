package it.unimib.gup.ui.main.group;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import it.unimib.gup.R;
import it.unimib.gup.adapter.DetailsGroupAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.viewmodels.GroupDetailsViewModel;

public class GroupDetailsFragment extends Fragment {

    private final static String TAG = "GroupDetailsFragment";

    private final String[] tabs = {"Posts", "Meetings"};

    private GroupDetailsViewModel mGroupDetailsViewModel;

    private Group group;


    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroupDetailsViewModel = new ViewModelProvider(requireActivity()).get(GroupDetailsViewModel.class);
        group = GroupDetailsFragmentArgs.fromBundle(getArguments()).getGroup();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_details, container, false);

        final TextView textViewGroupName = view.findViewById(R.id.details_groups_group_name);
        final View viewGroupColor = view.findViewById(R.id.details_groups_group_circle);
        final FrameLayout frameLayoutCategoryContainer = view.findViewById(R.id.details_groups_category_container);
        final TextView textViewGroupCategory = view.findViewById(R.id.details_groups_category_text);
        final TextView textViewSubCount = view.findViewById(R.id.subscriber_count);
        final TextView textViewGroupDescription = view.findViewById(R.id.details_groups_description);

        ImageView imageView = (ImageView) view.findViewById(R.id.group_details_image_view);

        GroupDetailsFragment thisRef = this;

        mGroupDetailsViewModel.getGroup(group.getId()).observe(getViewLifecycleOwner(), new Observer<GroupResponse>() {
            @Override
            public void onChanged(GroupResponse groupResponse) {

                if (groupResponse.getGroup() != null) {
                    Group tmpGroup = groupResponse.getGroup();

                    textViewGroupName.setText(tmpGroup.getName());
                    viewGroupColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(tmpGroup.getColor())));
                    frameLayoutCategoryContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(tmpGroup.getCategory().getColor())));
                    textViewGroupCategory.setText(tmpGroup.getCategory().getName());
                    textViewSubCount.setText(String.valueOf(tmpGroup.membersCount()));
                    textViewGroupDescription.setText(tmpGroup.getDescription());

                    Glide.with(thisRef).load(tmpGroup.getImageUrl()).
                            into(imageView);
                }

            }
        });


        final TabLayout tabLayout = view.findViewById(R.id.details_tab_layout);
        final ViewPager2 viewPager2 = view.findViewById(R.id.details_view_pager);

        DetailsGroupAdapter detailsGroupAdapter = new DetailsGroupAdapter(this, 2);
        viewPager2.setAdapter(detailsGroupAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();

        FloatingActionButton fab = view.findViewById(R.id.fab_group_details);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = tabLayout.getSelectedTabPosition();
                Log.d("Position: ", String.valueOf(position));

                requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.GONE);
                if(position == 0) {
                    GroupDetailsFragmentDirections.ActionGroupDetailsFragmentToCreatePostFragment action =
                            GroupDetailsFragmentDirections.actionGroupDetailsFragmentToCreatePostFragment(group);
                    Navigation.findNavController(view).navigate(action);
                }
                else {
                    GroupDetailsFragmentDirections.ActionGroupDetailsFragmentToCreateMeetingFragment
                            action = GroupDetailsFragmentDirections.actionGroupDetailsFragmentToCreateMeetingFragment(group);
                    Navigation.findNavController(view).navigate(action);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.group_details_menu, menu);
        MenuItem menuItem = menu.getItem(0);
        MenuItem menuItem1 = menu.getItem(1);
        if (group.getOwner().equals(mGroupDetailsViewModel.getCurrentUserId())) {
            menuItem.setVisible(true);
            menuItem1.setVisible(false);
        } else {
            menuItem.setVisible(false);
            menuItem1.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_leave_group) {
            mGroupDetailsViewModel.unsubscribe(group.getId());

            Navigation.findNavController(getView()).navigate(R.id.action_groupDetailsFragment_to_account);
        }

        return super.onOptionsItemSelected(item);
    }
}