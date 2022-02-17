package it.unimib.gup.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.BrowseGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;

public class BrowseFragment extends Fragment {

    private static final String TAG = "BrowseFragment";

    /* ELIMINARE */
    private Category tmpCategory;
    private Category tmpCategory2;
    private List<String> tmpUsersIds;
    private List<Meeting> tmpMeetingIds;
    private List<Post> tmpPosts;
    private List<Group> mGroups;
    /* --------- */

    private BrowseGroupsRecyclerViewAdapter adapter;
    private SearchView searchView;

    public BrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroups = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_browse, container, false);

        RecyclerView mBrowseGroupsRecyclerView = view.findViewById(R.id.browse_groups_recycler_view);
        mBrowseGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BrowseGroupsRecyclerViewAdapter(mGroups,
                new BrowseGroupsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Group group) {
                        Log.d(TAG, "onItemClick: " + group);
                        requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.GONE);
                        BrowseFragmentDirections.ActionBrowseToGroupDetailsFragment
                                action = BrowseFragmentDirections.actionBrowseToGroupDetailsFragment(group);
                        Navigation.findNavController(view).navigate(action);
                    }
                });
        mBrowseGroupsRecyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            view.findViewById(R.id.browse_no_results_container).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.browse_no_results_container).setVisibility(View.GONE);
        }

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setFilteredList(newText);
                if (adapter.getItemCount() == 0) {
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.GONE);
                }
                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}