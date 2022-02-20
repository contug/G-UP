package it.unimib.gup.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import it.unimib.gup.R;
import it.unimib.gup.viewmodels.AccountViewModel;
import it.unimib.gup.viewmodels.HomeViewModel;

public class ModifyAccount extends Fragment {

    AccountViewModel mAccountViewModel;

    public ModifyAccount() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modify_account, container, false);


        EditText firstNameRef = view.findViewById(R.id.name_modify);
        EditText lastNameRef = view.findViewById(R.id.surname_modify);
        EditText emailRef = view.findViewById(R.id.email_modify);

        Button buttonRef = view.findViewById(R.id.button_modify_account);

        buttonRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameRef.getText().toString();
                String lastName = lastNameRef.getText().toString();
                String email = emailRef.getText().toString();
                if(firstName.isEmpty() && lastName.isEmpty() && email.isEmpty()) {
                    updateUIForFailure();
                } else {
                    mAccountViewModel.editUser(firstName, lastName, email);
                    requireActivity().onBackPressed();
                }

            }
        });


        return view;
    }

    private void updateUIForFailure() {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), "Insert at least one filed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.VISIBLE);
    }
}