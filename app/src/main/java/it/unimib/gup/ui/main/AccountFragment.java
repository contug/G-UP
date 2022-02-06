package it.unimib.gup.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

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

import it.unimib.gup.R;
import it.unimib.gup.ui.authentication.AuthenticationActivity;
import it.unimib.gup.utils.SharedPreferencesProvider;

public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // It is necessary to specify that the toolbar has a custom menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
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