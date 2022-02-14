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
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.HomePostsRecyclerViewAdapter;
import it.unimib.gup.model.HomePost;
import it.unimib.gup.ui.main.group.CreateGroupFragment;

public class HomeFragment extends Fragment {

    private static final String TAG ="HomeFragment";

    private TextView textViewToolbar;

    /* ELIMINARE */
    private List<HomePost> mHomePosts;
    /* --------- */

    private HomePostsRecyclerViewAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        // It is necessary to specify that the toolbar has a custom menu
        setHasOptionsMenu(true);

        /* ELIMINARE */

        mHomePosts = Arrays.asList(
            new HomePost("group_id", "Appzoid",  "#38a9ff", "Luca Micheletto", "Qualcuno ha gli appunti per Programmazione di Dispositivi mobili?", new Date()),
            new HomePost("group_id", "Readers",  "#fbbf24", "Pietro Smusi", "Ragazzi dovete assolutamente leggere Moby Dick è veramente un bel libro, peccato che mi abbiano spoilerato la fine...", new Date()),
            new HomePost("group_id", "Appzoid",  "#38a9ff", "Giacomo Contu", "Potete cambiare la schermata di creazione dell'account da Activity a Fragment?", new Date())
        );

        Log.d("###", mHomePosts.toString());
        /* --------- */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mHomePostsRecyclerView = view.findViewById(R.id.home_posts_recycler_view);
        mHomePostsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HomePostsRecyclerViewAdapter(mHomePosts,
                new HomePostsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(HomePost post) {
                        Log.d(TAG, "onItemClick: " + post);
                    }
                });
        mHomePostsRecyclerView.setAdapter(adapter);

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
        Log.d(TAG, "onOptionsItemSelected: ");
        textViewToolbar = requireActivity().findViewById(R.id.toolbar_text_view);
        // Listener for the items in the custom menu
        if (item.getItemId() == R.id.add_group) {
            textViewToolbar.setVisibility(View.GONE);
            Navigation.findNavController(getView()).navigate(R.id.createGroupFragment);
        }
        return super.onOptionsItemSelected(item);
    }

}