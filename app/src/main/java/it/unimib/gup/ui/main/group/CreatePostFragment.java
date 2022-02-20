package it.unimib.gup.ui.main.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;
import it.unimib.gup.viewmodels.CreatePostViewModel;


public class CreatePostFragment extends Fragment {

    public CreatePostViewModel mCreatePostViewModel;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CreatePost", "onCreate");
        mCreatePostViewModel = new ViewModelProvider(requireActivity()).get(CreatePostViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        Group group = CreatePostFragmentArgs.fromBundle(getArguments()).getGroup();


        final EditText postMessage = view.findViewById(R.id.edit_text_create_description);
        final Button buttonCreatePost = view.findViewById(R.id.button_create_post);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        buttonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = postMessage.getText().toString();
                if(!text.isEmpty()) {
                    mCreatePostViewModel.addPost(group.getId(), text);
                    requireActivity().onBackPressed();
                } else {
                    updateUIForFailure();
                }
            }
        });

        return view;
    }

    private void updateUIForFailure() {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), "Invalid Post", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.VISIBLE);
    }
}