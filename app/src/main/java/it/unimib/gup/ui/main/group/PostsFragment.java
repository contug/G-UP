package it.unimib.gup.ui.main.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.Arrays;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.PostsRecyclerViewAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Post;
import it.unimib.gup.ui.main.BrowseFragmentDirections;

public class PostsFragment extends Fragment {

    private static final String TAG = "BrowseFragment";

    /* ELIMINARE */
    private List<Post> mPosts;
    /* --------- */

    private PostsRecyclerViewAdapter adapter;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ELIMINARE */
        mPosts = Arrays.asList(
                new Post("note_id_1", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_1"),
                new Post("note_id_2", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_2"),
                new Post("note_id_3", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_3"),
                new Post("note_id_4", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_4"),
                new Post("note_id_5", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_5"),
                new Post("note_id_6", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_6"),
                new Post("note_id_7", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_7"),
                new Post("note_id_8", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_8"),
                new Post("note_id_9", "Author_name", "Post molto lungo per capire se va a capo, asdasdasd asd as das d asdasd asd as  note_text_9")
        );
        /* --------- */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView mPostsRecyclerView = view.findViewById(R.id.posts_recycler_view);
        mPostsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PostsRecyclerViewAdapter(mPosts,
                new PostsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Post post) {
                        Log.d(TAG, "onItemClick: " + post);
                    }
                });
        mPostsRecyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;
    }
}
