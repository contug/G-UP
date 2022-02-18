package it.unimib.gup.ui.main.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.PostsRecyclerViewAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.HomePost;
import it.unimib.gup.model.Post;
import it.unimib.gup.model.User;
import it.unimib.gup.ui.main.BrowseFragmentDirections;

public class PostsFragment extends Fragment {

    private static final String TAG = "PostsFragment";

    private List<Post> mPosts;
    private GroupViewModel mGroupViewModel;

    private PostsRecyclerViewAdapter adapter;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPosts = new ArrayList<>();

        mGroupViewModel = ViewModelProviders.of(requireActivity()).get(GroupViewModel.class);
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

        mGroupViewModel.getGroup(mGroupViewModel.getCurrentGroupId()).observe(getViewLifecycleOwner(), new Observer<Group>() {
            @Override
            public void onChanged(Group group) {
                mPosts.clear();

                if(group.getPosts() != null) {

                    List<Post> list = new ArrayList<>(group.getPosts().values());

                    for (Post post : list) {
                        User tmpUser = mGroupViewModel.getUser(post.getAuthor());
                        post.setAuthor(tmpUser.getFirstName() + " " + tmpUser.getLastName());
                    }

                    mPosts.addAll(list);
                }
                adapter.notifyDataSetChanged();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
