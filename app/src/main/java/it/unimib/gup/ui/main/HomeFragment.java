package it.unimib.gup.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.HomePostsRecyclerViewAdapter;
import it.unimib.gup.model.HomePost;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG ="HomeFragment";

    private List<HomePost> mHomePosts;

    private HomePostsRecyclerViewAdapter adapter;

    private HomeViewModel mHomeViewModel;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setHasOptionsMenu(true);

        mHomeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        mHomePosts = new ArrayList<HomePost>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mHomePostsRecyclerView = view.findViewById(R.id.home_posts_recycler_view);

        mHomePostsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mHomeViewModel.getSubscriptions().observe(getViewLifecycleOwner(), new Observer<SubscriptionsResponse>() {
            @Override
            public void onChanged(SubscriptionsResponse subscriptionsResponse) {
                mHomePosts.clear();

                if (subscriptionsResponse.getHomePosts() != null) {
                    mHomePosts.addAll(subscriptionsResponse.getHomePosts());
                }

                if(adapter.getItemCount() == 0){
                    view.findViewById(R.id.home_no_results_container).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.home_no_results_container).setVisibility(View.GONE);
                }

                adapter.notifyDataSetChanged();
            }
        });

        adapter = new HomePostsRecyclerViewAdapter(mHomePosts,
                new HomePostsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(HomePost post) {
                        Log.d(TAG, "onItemClick: " + post);
                    }
                });

        mHomePostsRecyclerView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        if (item.getItemId() == R.id.add_group) {
            requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.GONE);
            Navigation.findNavController(getView()).navigate(R.id.createGroupFragment);
        }
        return super.onOptionsItemSelected(item);
    }

}