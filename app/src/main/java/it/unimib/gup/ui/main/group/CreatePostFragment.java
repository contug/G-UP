package it.unimib.gup.ui.main.group;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;
import it.unimib.gup.model.Post;


public class CreatePostFragment extends Fragment {

    public GroupViewModel groupViewModel;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CreatePost", "onCreate");
        groupViewModel = new ViewModelProvider(requireActivity()).get(GroupViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        Group group = CreatePostFragmentArgs.fromBundle(getArguments()).getGroup();

        final EditText postMessage = view.findViewById(R.id.edit_text_create_post);
        final Button buttonCreatePost = view.findViewById(R.id.button_create_post);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        buttonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String author = mAuth.getUid();
                String text = postMessage.getText().toString();
                if(!text.isEmpty()) {
                    Post post = new Post(author, text);
                    groupViewModel.savePost(group.getId(), post);
                }

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.VISIBLE);
    }
}