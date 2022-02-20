package it.unimib.gup.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.adapter.AccountGroupsRecyclerViewAdapter;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.responses.SubscriptionsResponse;
import it.unimib.gup.ui.authentication.AuthenticationActivity;
import it.unimib.gup.ui.authentication.UserViewModel;
import it.unimib.gup.utils.SharedPreferencesProvider;
import it.unimib.gup.viewmodels.AccountViewModel;
import it.unimib.gup.viewmodels.HomeViewModel;

public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    private TextView textViewAccountFragment;

    private UserViewModel mUserViewModel;
    private AccountViewModel mAccountViewModel;

    private List<Group> mGroups;


    private AccountGroupsRecyclerViewAdapter adapter;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mAccountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        mGroups = new ArrayList<>();
        // It is necessary to specify that the toolbar has a custom menu
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        textViewAccountFragment = view.findViewById(R.id.text_view_account_fragment);

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

        mAccountViewModel.getSubscriptions().observe(getViewLifecycleOwner(), new Observer<SubscriptionsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(SubscriptionsResponse subscriptionsResponse) {
                Log.d(TAG, "onChanged: ");
                mGroups.clear();

                if (subscriptionsResponse.getGroups() != null) {
                    mGroups.addAll(subscriptionsResponse.getGroups());
                }
                if (adapter.getItemCount() == 0) {
                    textViewAccountFragment.setText(R.string.not_a_member);
                } else if (adapter.getItemCount() == 1) {
                    textViewAccountFragment.setText(R.string.member_of_one_group);
                } else {
                    textViewAccountFragment.setText("You are member of " + adapter.getItemCount() + " groups");
                }

                adapter.notifyDataSetChanged();
            }
        });

        if (adapter.getItemCount() == 0) {
            textViewAccountFragment.setText(R.string.not_a_member);
        }

        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.account_fragment_menu, menu);
        if (mUserViewModel.getAuthWithGoogle()) {
            menu.getItem(0).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_logout) {
            Log.d(TAG, "onOptionsItemSelected: Logout");
            FirebaseAuth.getInstance().signOut();
            SharedPreferencesProvider sharedPreferencesProvider = new SharedPreferencesProvider(requireActivity().getApplication());
            sharedPreferencesProvider.deleteAll();
            startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
            requireActivity().finish();
        } else if (item.getItemId() == R.id.menu_item_edit_profile) {
            Log.d(TAG, "onOptionsItemSelected: Edit profile");
            requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.GONE);
            Navigation.findNavController(getView()).navigate(R.id.modifyAccount);
        }
        return super.onOptionsItemSelected(item);
    }
}