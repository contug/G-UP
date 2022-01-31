package it.unimib.gup.ui.authentication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.unimib.gup.R;

public class LoginFragment extends Fragment {

    TextView groupUp;
    EditText email, password;
    Button login;
    float opacityValue = 0;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        groupUp = root.findViewById(R.id.text_view_signin);
        email = root.findViewById(R.id.email_log);
        password = root.findViewById(R.id.password_log);
        login = root.findViewById(R.id.button_login);

        groupUp.setTranslationX(800);
        email.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);

        groupUp.setAlpha(opacityValue);
        email.setAlpha(opacityValue);
        password.setAlpha(opacityValue);
        login.setAlpha(opacityValue);

        groupUp.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        email.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        password.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        login.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        return root;
    }
}