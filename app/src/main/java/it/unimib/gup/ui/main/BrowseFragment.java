package it.unimib.gup.ui.main;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.BrowseGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.responses.GroupListResponse;
import it.unimib.gup.viewmodels.BrowseGroupsViewModel;

public class BrowseFragment extends Fragment {

    private static final String TAG = "BrowseFragment";

    private List<Group> mGroups;

    private BrowseGroupsRecyclerViewAdapter adapter;
    private SearchView searchView;
    private BrowseGroupsViewModel mBrowseGroupViewModel;

    public BrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrowseGroupViewModel = new ViewModelProvider(requireActivity()).get(BrowseGroupsViewModel.class);
        mGroups = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        RecyclerView mBrowseGroupsRecyclerView = view.findViewById(R.id.browse_groups_recycler_view);
        mBrowseGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Observer<GroupListResponse> observer = new Observer<GroupListResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(GroupListResponse listGroupResponse) {
                mGroups.clear();

                if (listGroupResponse.getGroups() != null) {
                    mGroups.addAll(listGroupResponse.getGroups());
                    adapter.setGroupListAll(listGroupResponse.getGroups());

                    adapter.notifyDataSetChanged();
                }

                if (adapter.getItemCount() == 0) {
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.browse_no_results_container).setVisibility(View.GONE);
                }
            }
        };

        mBrowseGroupViewModel.getGroups().observe(getViewLifecycleOwner(), observer);

        adapter = new BrowseGroupsRecyclerViewAdapter(mGroups,
                new BrowseGroupsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Group group) {
                        BrowseFragmentDirections.ActionBrowseToGroupDetailsFragment
                                action = BrowseFragmentDirections.actionBrowseToGroupDetailsFragment(group);

                        Navigation.findNavController(view).navigate(action);
                    }
                }, new BrowseGroupsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Group group) {
                mBrowseGroupViewModel.subscribe(group.getId());
            }
        });

        mBrowseGroupsRecyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);
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