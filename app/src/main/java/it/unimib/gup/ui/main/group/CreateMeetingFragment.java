package it.unimib.gup.ui.main.group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;
import it.unimib.gup.viewmodels.CreateMeetingViewModel;
import it.unimib.gup.viewmodels.CreatePostViewModel;

public class CreateMeetingFragment extends Fragment {

    public CreateMeetingViewModel mCreateMeetingViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mCreateMeetingViewModel = new ViewModelProvider(requireActivity()).get(CreateMeetingViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_meeting, container, false);

        Group groupBundle = CreatePostFragmentArgs.fromBundle(getArguments()).getGroup();

        final Spinner spinnerMeetingType = view.findViewById(R.id.spinner_create_meeting);

        spinnerMeetingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        Button mCreateMeetngButton = view.findViewById(R.id.button_create_meeting);
        EditText mMeetingUrl = view.findViewById(R.id.input_text_link_or_position);
        EditText mMeetingDate = view.findViewById(R.id.editTextDate);

        mCreateMeetngButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = spinnerMeetingType.getSelectedItem().toString().toLowerCase();
                String info = mMeetingUrl.getText().toString();
                String date = mMeetingDate.getText().toString();
                if(!info.isEmpty() && !date.isEmpty() && !type.isEmpty()) {
                    mCreateMeetingViewModel.addMeeting(groupBundle.getId(), type, info, date);
                    requireActivity().onBackPressed();
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