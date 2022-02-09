package it.unimib.gup.ui.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import it.unimib.gup.R;
import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.ui.MainActivity;
import it.unimib.gup.utils.Constants;

/**
 * It shows the login screen.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private UserViewModel mUserViewModel;
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> mGoogleSignUpActivityResult;

    private String email;
    private String password;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        mGoogleSignUpActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        mUserViewModel.signUpWithGoogle(data).observe(getViewLifecycleOwner(), new Observer<AuthenticationResponse>() {
                            @Override
                            public void onChanged(AuthenticationResponse authenticationResponse) {
                                if (authenticationResponse.isSuccess()) {
                                    startActivity(new Intent(requireActivity(), MainActivity.class));
                                    requireActivity().finish();
                                } else {
                                    updateUIForFailure(authenticationResponse.getMessage());
                                }
                            }
                        });
                    } else {
                        Log.d(TAG, "Error with Google Registration");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        float opacityValue = 0;
        //final TextView textViewGroupUp = root.findViewById(R.id.text_view_signin);
        final EditText editTextEmail = root.findViewById(R.id.email_log);
        final EditText editTextPassword = root.findViewById(R.id.password_log);
        final Button buttonLogin = root.findViewById(R.id.button_login);

        final FloatingActionButton buttonGoogle = requireActivity().findViewById(R.id.fab_google);

        //textViewGroupUp.setTranslationX(800);
        editTextEmail.setTranslationX(800);
        editTextPassword.setTranslationX(800);
        buttonLogin.setTranslationX(800);

        //textViewGroupUp.setAlpha(opacityValue);
        editTextEmail.setAlpha(opacityValue);
        editTextPassword.setAlpha(opacityValue);
        buttonLogin.setAlpha(opacityValue);

        //textViewGroupUp.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        editTextEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        editTextPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        buttonLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        // The observer associated with the LiveData AuthenticationResponse
        Observer<AuthenticationResponse> observer = new Observer<AuthenticationResponse>() {
            @Override
            public void onChanged(AuthenticationResponse authenticationResponse) {
                if (authenticationResponse != null) {
                    if (authenticationResponse.isSuccess()) {
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                        requireActivity().finish();
                    } else {
                        updateUIForFailure(authenticationResponse.getMessage());
                    }
                }
            }
        };

        // Listener for button Login With Email and Password
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    mUserViewModel.signInWithEmail(email, password).observe(getViewLifecycleOwner(), observer);
                } else {
                    updateUIForFailure(requireActivity().getApplication().getString(R.string.invalid_auth_input));
                }
            }
        });

        // Listener for FloatingActionButton SignUp With Google
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Configure Google Sign In
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(Constants.DEFAULT_WEB_CLIENT_ID)
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                mGoogleSignUpActivityResult.launch(signInIntent);
            }
        });

        return root;
    }

    private void updateUIForFailure(String message) {
        final FloatingActionButton buttonGoogle = requireActivity().findViewById(R.id.fab_google);
        final FloatingActionButton buttonFacebook = requireActivity().findViewById(R.id.fab_facebook);
        final FloatingActionButton buttonTwitter = requireActivity().findViewById(R.id.fab_twitter);

        final Snackbar snack = Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);

        snack.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                buttonFacebook.setVisibility(View.VISIBLE);
                buttonGoogle.setVisibility(View.VISIBLE);
                buttonTwitter.setVisibility((View.VISIBLE));
            }

            @Override
            public void onShown(Snackbar sb) {
                buttonFacebook.setVisibility(View.INVISIBLE);
                buttonGoogle.setVisibility(View.INVISIBLE);
                buttonTwitter.setVisibility((View.INVISIBLE));
            }
        });

        snack.show();
        mUserViewModel.clear();
    }
}