package it.unimib.gup.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.BrowseGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Note;

public class BrowseFragment extends Fragment {

    private static final String TAG = "BrowseFragmentFragment";

    /* ELIMINARE */
    private Category tmpCategory;
    private List<String> tmpUsersIds;
    private List<Meeting> tmpMeetingIds;
    private List<Note> tmpNotes;
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

        /* ELIMINARE */
        tmpCategory = new Category("SCIENCE", "#F43F5E");

        tmpUsersIds = Arrays.asList("user_id_1", "user_id_2", "user_id_3");

        tmpMeetingIds = Arrays.asList(new Meeting(Meeting.MeetingType.offline, new Date(), "maps"), new Meeting(Meeting.MeetingType.online, new Date(), "url"));

        tmpNotes = Arrays.asList(new Note("note_id_1", "user_id_1", "Note text 1"), new Note("note_id_2", "user_id_2", "Note text 2"));

        mGroups = Arrays.asList(new Group("group_id", "GoGet'Em", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpNotes, "#22C55E"), new Group("group_id", "Meta", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpNotes, "#22C55E"), new Group("group_id", "Appzoid", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpNotes, "#22C55E"));

        Log.d("###", mGroups.toString());
        /* --------- */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("###", mGroups.toString());
        View view = inflater.inflate(R.layout.fragment_browse, container, false);

        RecyclerView mBrowseGroupsRecyclerView = view.findViewById(R.id.browse_groups_recycler_view);
        mBrowseGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BrowseGroupsRecyclerViewAdapter(mGroups,
                new BrowseGroupsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Group group) {
                        Log.d(TAG, "onItemClick: " + group);
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
                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}