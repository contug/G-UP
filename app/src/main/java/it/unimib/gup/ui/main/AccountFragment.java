package it.unimib.gup.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.AccountGroupsRecyclerViewAdapter;
import it.unimib.gup.adapter.BrowseGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.Post;
import it.unimib.gup.ui.authentication.AuthenticationActivity;
import it.unimib.gup.utils.SharedPreferencesProvider;

public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    /* ELIMINARE */
    private Category tmpCategory;
    private List<String> tmpUsersIds;
    private List<Meeting> tmpMeetingIds;
    private List<Post> tmpPosts;
    private List<Group> mGroups;
    /* --------- */


    private AccountGroupsRecyclerViewAdapter adapter;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // It is necessary to specify that the toolbar has a custom menu


        /* ELIMINARE */
        tmpCategory = new Category("SCIENCE", "#F43F5E");

        tmpUsersIds = Arrays.asList("user_id_1", "user_id_2", "user_id_3");

        tmpMeetingIds = Arrays.asList(new Meeting(Meeting.MeetingType.offline, new Date(), "maps"), new Meeting(Meeting.MeetingType.online, new Date(), "url"));

        tmpPosts = Arrays.asList(new Post("note_id_1", "user_id_1", "Post text 1"), new Post("note_id_2", "user_id_2", "Post text 2"));

        mGroups = Arrays.asList(
                new Group("group_id", "GoGet'Em", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"),
                new Group("group_id", "Meta", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"),
                new Group("group_id", "Appzoid", "Descrizione del gruppo non troppo lunga altrimenti è brutta da vedere sulla schermata di home e viene tronc...", tmpCategory, tmpUsersIds, tmpMeetingIds, tmpPosts, "#22C55E"));

        Log.d("###", mGroups.toString());
        /* --------- */

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        RecyclerView mBrowseGroupsRecyclerView = view.findViewById(R.id.account_your_groups_recycler_view);
        mBrowseGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapter = new AccountGroupsRecyclerViewAdapter(mGroups,
                new AccountGroupsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Group group) {
                        Log.d(TAG, "onItemClick: " + group);
                        AccountFragmentDirections.ActionAccountToGroupDetailsFragment
                                action = AccountFragmentDirections.actionAccountToGroupDetailsFragment(group);
                        Navigation.findNavController(view).navigate(action);
                    }
                });
        mBrowseGroupsRecyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        PopupMenu popupMenu = new PopupMenu(requireActivity(), requireView());
        popupMenu.setGravity(Gravity.BOTTOM);
        inflater.inflate(R.menu.account_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Log.d(TAG, "onOptionsItemSelected: Logout");
            FirebaseAuth.getInstance().signOut();
            SharedPreferencesProvider sharedPreferencesProvider = new SharedPreferencesProvider(requireActivity().getApplication());
            sharedPreferencesProvider.deleteAll();
            startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
            requireActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}