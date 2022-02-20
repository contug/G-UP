package it.unimib.gup.ui.main.group;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import it.unimib.gup.R;
import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;
import it.unimib.gup.viewmodels.GroupDetailsViewModel;

public class EditGroupFragment extends Fragment {

    private GroupDetailsViewModel mGroupDetailsViewModel;

    public EditGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroupDetailsViewModel = new ViewModelProvider(requireActivity()).get(GroupDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_group, container, false);

        EditText editTextEditGroupName = view.findViewById(R.id.edit_text_edit_group_name);
        Spinner spinnerEditGroupCategory = view.findViewById(R.id.spinner_edit_group_category);
        EditText editTextEditGroupDescription = view.findViewById(R.id.edit_text_edit_description);

        Button buttonEditGroup = view.findViewById(R.id.button_edit_group);

        spinnerEditGroupCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        buttonEditGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameGroup = editTextEditGroupName.getText().toString();
                String categoryNameGroup = spinnerEditGroupCategory.getSelectedItem().toString();
                String descriptionGroup = editTextEditGroupDescription.getText().toString();
                if (spinnerEditGroupCategory.getSelectedItemPosition() != 0) {
                    mGroupDetailsViewModel.editGroup(nameGroup, descriptionGroup, new Category(categoryNameGroup));
                    requireActivity().onBackPressed();
                } else {
                    updateUIForFailure();
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

    private void updateUIForFailure() {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), "Select Category", Snackbar.LENGTH_SHORT).show();
    }
}