package it.unimib.gup.ui.main.group;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.model.responses.UserResponse;
import it.unimib.gup.ui.main.BrowseFragmentDirections;
import it.unimib.gup.viewmodels.GroupDetailsPostsViewModel;
import it.unimib.gup.viewmodels.GroupDetailsViewModel;

public class PostsFragment extends Fragment {

    private static final String TAG = "PostsFragment";

    private List<Post> mPosts;
    private GroupDetailsViewModel mGroupDetailsViewModel;
    private Group group;

    private PostsRecyclerViewAdapter adapter;

    public PostsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPosts = new ArrayList<>();

        mGroupDetailsViewModel = ViewModelProviders.of(requireActivity()).get(GroupDetailsViewModel.class);

        group = GroupDetailsFragmentArgs.fromBundle(getParentFragment().getArguments()).getGroup();
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



        mGroupDetailsViewModel.getGroup(group.getId()).observe(getViewLifecycleOwner(), new Observer<GroupResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(GroupResponse groupResponse) {

                mPosts.clear();

                if (groupResponse.getGroup().getPosts() != null) {
                    List<Post> list = new ArrayList<>(groupResponse.getGroup().getPosts().values());

                    mPosts.addAll(list);
                }

                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
