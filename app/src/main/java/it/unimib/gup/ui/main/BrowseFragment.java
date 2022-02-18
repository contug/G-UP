package it.unimib.gup.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import it.unimib.gup.model.GroupsResponse;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;
import it.unimib.gup.ui.main.group.GroupViewModel;

public class BrowseFragment extends Fragment {

    private static final String TAG = "BrowseFragment";

    /* ELIMINARE */
    private List<Group> mGroups;
    /* --------- */

    private BrowseGroupsRecyclerViewAdapter adapter;
    private SearchView searchView;
    private GroupViewModel mGroupViewModel;

    public BrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroupViewModel = new ViewModelProvider(requireActivity()).get(GroupViewModel.class);
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
                        BrowseFragmentDirections.ActionBrowseToGroupDetailsFragment
                                action = BrowseFragmentDirections.actionBrowseToGroupDetailsFragment(group);
                        Navigation.findNavController(view).navigate(action);
                    }
                });
        mBrowseGroupsRecyclerView.setAdapter(adapter);


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

        mGroupViewModel.getGroups().observe(getViewLifecycleOwner(), new Observer<GroupsResponse>() {
            @Override
            public void onChanged(GroupsResponse groupsResponse) {

                Log.d(TAG, "onChanged: ");
                
                mGroups.clear();
                adapter.setGroupListAll(groupsResponse.getGroups());
                mGroups.addAll(groupsResponse.getGroups());

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

                if (adapter.getItemCount() == 0) {
                   // Log.d(TAG, "onChanged: vuoto");
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.VISIBLE);
                } else {
                   // Log.d(TAG, "onChanged: non vuoto");
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.GONE);
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}