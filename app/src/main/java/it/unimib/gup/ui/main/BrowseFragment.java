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

    private TextView textViewToolbar;

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
        textViewToolbar = requireActivity().findViewById(R.id.toolbar_text_view);

        /* ELIMINARE */
        tmpCategory = new Category("Formal Sciences", "#F43F5E");
        tmpCategory2 = new Category("Humanities", "#c2410c");

        tmpUsersIds = Arrays.asList("user_id_1", "user_id_2", "user_id_3");

        tmpMeetingIds = Arrays.asList(new Meeting(Meeting.MeetingType.offline, new Date(), "maps"), new Meeting(Meeting.MeetingType.online, new Date(), "url"));

        tmpPosts = Arrays.asList(new Post("note_id_1", "user_id_1", "Post text 1"), new Post("note_id_2", "user_id_2", "Post text 2"));

        mGroups = Arrays.asList(
                new Group("group_id", "Appzoid", "Sviluppo di un'applicazione per la per l'organizzazione di studio di gruppo", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#38a9ff"),
                new Group("group_id", "Readers", "Ragazzi a cui piace leggere libri", tmpCategory2, tmpUsersIds, tmpMeetingIds, tmpPosts, "#fbbf24"),
                new Group("group_id", "Analisi 2", "Gruppo studio per analisi 2 dell'Università di Milano-Bicocca", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E")
        );

        Log.d("###", mGroups.toString());
        /* --------- */
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
                        textViewToolbar.setVisibility(View.GONE);
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

        // Inflate the layout for this fragment
        return view;
    }
}