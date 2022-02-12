package it.unimib.gup.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.HomeGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;

public class HomeFragment extends Fragment {

    private static final String TAG ="HoveFragment";

    /* ELIMINARE */
    private Category tmpCategory;
    private List<String> tmpUsersIds;
    private List<Meeting> tmpMeetingIds;
    private List<Post> tmpPosts;
    private List<Group> mGroups;
    /* --------- */

    private HomeGroupsRecyclerViewAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // It is necessary to specify that the toolbar has a custom menu
        setHasOptionsMenu(true);

        /* ELIMINARE */
        tmpCategory = new Category("SCIENCE", "#F43F5E");

        tmpUsersIds = Arrays.asList("user_id_1", "user_id_2", "user_id_3");

        tmpMeetingIds = Arrays.asList(new Meeting(Meeting.MeetingType.offline, new Date(), "maps"), new Meeting(Meeting.MeetingType.online, new Date(), "url"));

        tmpPosts = Arrays.asList(new Post("note_id_1", "user_id_1", "Post text 1"), new Post("note_id_2", "user_id_2", "Post text 2"));

        mGroups = Arrays.asList(new Group("group_id", "GoGet'Em", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"), new Group("group_id", "Meta", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"), new Group("group_id", "Appzoid", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"));

        Log.d("###", mGroups.toString());
        /* --------- */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mHomeGroupsRecyclerView = view.findViewById(R.id.home_groups_recycler_view);
        mHomeGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HomeGroupsRecyclerViewAdapter(mGroups,
                new HomeGroupsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Group group) {
                        Log.d(TAG, "onItemClick: " + group);
                        HomeFragmentDirections.ActionHomeToGroupDetailsFragment
                                action = HomeFragmentDirections.actionHomeToGroupDetailsFragment(group);
                        Navigation.findNavController(view).navigate(action);
                    }
                });
        mHomeGroupsRecyclerView.setAdapter(adapter);

        if(adapter.getItemCount() == 0){
            view.findViewById(R.id.home_no_results_container).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.home_no_results_container).setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Non sono sicuro se il super serva oppure no, ginelli l'ha tolto
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Listener for the items in the custom menu
        if (item.getItemId() == R.id.add_group) {
            Intent intent = new Intent(requireActivity(), CreateGroupActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}