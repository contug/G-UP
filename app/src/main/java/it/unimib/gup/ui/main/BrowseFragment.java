package it.unimib.gup.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
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

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.BrowseGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.viewmodels.BrowseGroupsViewModel;
import it.unimib.gup.viewmodels.BrowseGroupsViewModelFactory;

public class BrowseFragment extends Fragment {

    private static final String TAG = "BrowseFragment";

    private BrowseGroupsViewModel mGroupsViewModel;
    private BrowseGroupsRecyclerViewAdapter mGroupsAdapter;
    private RecyclerView mGroupsRecyclerView;


    private SearchView mSearchView;

    private List<Group> mGroups;

    private View mEmptyStateContainer;

    public BrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mGroups == null) {
            mGroups = new ArrayList<>();
        }

        mGroupsViewModel = new ViewModelProvider(requireActivity(), new BrowseGroupsViewModelFactory("user_id")).get(BrowseGroupsViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Constants
        View mView = inflater.inflate(R.layout.fragment_browse, container, false);
        LifecycleOwner mLifecycleOwner = getViewLifecycleOwner();
        Context mContext = mView.getContext();





        // Find ui elements
        mGroupsRecyclerView = mView.findViewById(R.id.browse_groups_recycler_view);
        mSearchView = mView.findViewById(R.id.searchView);
        mEmptyStateContainer = mView.findViewById(R.id.browse_no_results_container);


        // Initialize Adapter

        mGroupsAdapter = new BrowseGroupsRecyclerViewAdapter(mGroups,
                group -> {
                    Log.d(TAG, "onItemClick: " + group.toString());

                    BrowseFragmentDirections.ActionBrowseToGroupDetailsFragment action = BrowseFragmentDirections.actionBrowseToGroupDetailsFragment(group);
                    Navigation.findNavController(mView).navigate(action);
                });



        mGroupsViewModel.getGroups().observe(mLifecycleOwner, new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groupList) {
                mGroups.clear();
                mGroups.addAll(groupList);

                if (mGroupsAdapter.getItemCount() == 0) {
                    mEmptyStateContainer.setVisibility(View.VISIBLE);
                } else {
                    mEmptyStateContainer.setVisibility(View.GONE);
                }

                mGroupsAdapter.notifyDataSetChanged();
            }
        });


        mGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mGroupsRecyclerView.setAdapter(mGroupsAdapter);





        // Filter adapter
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newSearchValue) {
                mGroupsAdapter.setFilteredList(newSearchValue);

                if (mGroupsAdapter.getItemCount() == 0) {
                    mEmptyStateContainer.setVisibility(View.VISIBLE);
                } else {
                    mEmptyStateContainer.setVisibility(View.GONE);
                }
                return true;
            }
        });


        return mView;
    }
}