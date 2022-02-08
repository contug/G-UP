package it.unimib.gup.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import it.unimib.gup.R;
import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.model.User;
import it.unimib.gup.ui.MainActivity;

/**
 * It shows the registration screen.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    private UserViewModel mUserViewModel;
    private FirebaseAuth mAuth;

    private User mUser;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText editTextName = root.findViewById(R.id.name);
        final EditText editTextSurname = root.findViewById(R.id.surname);
        final EditText editTextEmail = root.findViewById(R.id.email_reg);
        final EditText editTextPassword = root.findViewById(R.id.password_reg);

        final Button buttonRegister = root.findViewById(R.id.button_sign_up);

        // The Observer associated with the LiveData Boolean to see if user was saved on the Database
        final Observer<Boolean> observerSaveUserOnFirebaseDatabase = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if (response) {
                    Log.d(TAG, "User saved on Firebase");
                } else {
                    Log.d(TAG, "User not saved on Firebase");
                }
            }
        };

        // The Observer associated with the LiveData AuthenticationResponse
        final Observer<AuthenticationResponse> observerSaveUserOnFirebaseAuth = new Observer<AuthenticationResponse>() {
            @Override
            public void onChanged(AuthenticationResponse authenticationResponse) {
                if (authenticationResponse != null) {
                    if (authenticationResponse.isSuccess()) {
                        mUser = new User();
                        mUser.setId(mAuth.getUid());
                        mUser.setEmail(mAuth.getCurrentUser().getEmail());
                        mUserViewModel.saveUser(mUser).observe(getViewLifecycleOwner(), observerSaveUserOnFirebaseDatabase);
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                        requireActivity().finish();
                    } else {
                        updateUIForFailure(authenticationResponse.getMessage());
                    }
                }
            }
        };


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    mUserViewModel.signUpWithEmail(name, surname, email, password).observe(getViewLifecycleOwner(), observerSaveUserOnFirebaseAuth);
                } else {
                    updateUIForFailure(requireActivity().getApplication().getString(R.string.invalid_auth_input));
                }
            }
        });

        return root;
    }

    /**
     * It shows a warning message to the user with a Snackbar.
     * @param message The warning message to be shown in the Snackbar.
     */
    private void updateUIForFailure(String message) {
        final FloatingActionButton buttonGoogle = requireActivity().findViewById(R.id.fab_google);
        final FloatingActionButton buttonFacebook = requireActivity().findViewById(R.id.fab_facebook);
        final FloatingActionButton buttonTwitter = requireActivity().findViewById(R.id.fab_twitter);

        final Snackbar snack = Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);

        snack.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                buttonFacebook.setVisibility(View.VISIBLE);
                buttonGoogle.setVisibility(View.VISIBLE);
                buttonTwitter.setVisibility((View.VISIBLE));
            }
            @Override
            public void onShown(Snackbar snackbar) {
                buttonFacebook.setVisibility(View.INVISIBLE);
                buttonGoogle.setVisibility(View.INVISIBLE);
                buttonTwitter.setVisibility((View.INVISIBLE));
            }
        });

        snack.show();
        mUserViewModel.clear();
    }
}