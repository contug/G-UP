package it.unimib.gup.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import it.unimib.gup.R;
import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.ui.MainActivity;

/**
 * It shows the registration screen.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    private UserViewModel mUserViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText editTextName = view.findViewById(R.id.name);
        final EditText editTextSurname = view.findViewById(R.id.surname);
        final EditText editTextEmail = view.findViewById(R.id.email_reg);
        final EditText editTextPassword = view.findViewById(R.id.password_reg);

        final Button buttonRegister = view.findViewById(R.id.button_sign_up);

        // The Observer associated with the LiveData AuthenticationResponse
        final Observer<AuthenticationResponse> observer = new Observer<AuthenticationResponse>() {
            @Override
            public void onChanged(AuthenticationResponse authenticationResponse) {
                if (authenticationResponse != null) {
                    if (authenticationResponse.isSucces()) {
                        startActivity(new Intent(requireActivity(), MainActivity.class));
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

                mUserViewModel.signUpWithEmail(name, surname, email, password).observe(getViewLifecycleOwner(), observer);
            }
        });

        return view;
    }

    /**
     * It shows a warning message to the user with a Snackbar.
     * @param message The warning message to be shown in the Snackbar.
     */
    private void updateUIForFailure(String message) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
        mUserViewModel.clear();
    }
}