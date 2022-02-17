package it.unimib.gup.ui.main.group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

import it.unimib.gup.R;
import it.unimib.gup.model.Category;
import it.unimib.gup.repository.groups.GroupsRepository;

@IgnoreExtraProperties
public class CreateGroupFragment extends Fragment {

    public static final String TAG = "CREATE_GROUP_ACTIVITY";

    private GroupViewModel mGroupViewModel;
    private TextView textViewToolbar;
    private GroupsRepository mGroupRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewToolbar = requireActivity().findViewById(R.id.toolbar_text_view);
        mGroupViewModel = new ViewModelProvider(requireActivity()).get(GroupViewModel.class);

        mGroupRepository = new GroupsRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);

        final EditText editTextNameGroup = view.findViewById(R.id.edit_text_create_group_name);
        final Spinner spinnerCategoryGroup = view.findViewById(R.id.spinner_create_group_category);
        final EditText editTextDescriptionGroup = view.findViewById(R.id.edit_text_create_group_description);
        final Button buttonCreateGroup = view.findViewById(R.id.button_create_group);

        spinnerCategoryGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    TextView textView = (TextView)view;
                    textView.setTextColor(Color.GRAY);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameGroup = editTextNameGroup.getText().toString();
                String categoryNameGroup = spinnerCategoryGroup.getSelectedItem().toString();
                String descriptionGroup = editTextDescriptionGroup.getText().toString();
                if (!nameGroup.isEmpty() && spinnerCategoryGroup.getSelectedItemPosition() != 0) {

                }
            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        textViewToolbar.setVisibility(View.VISIBLE);
    }
}