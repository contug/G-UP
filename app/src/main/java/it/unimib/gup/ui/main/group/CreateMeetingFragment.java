package it.unimib.gup.ui.main.group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;
import it.unimib.gup.viewmodels.CreateMeetingViewModel;

public class CreateMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public CreateMeetingViewModel mCreateMeetingViewModel;

    private TextView mMeetingDate;

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


        Button mCreateMeetingButton = view.findViewById(R.id.button_create_meeting);
        EditText mMeetingUrl = view.findViewById(R.id.input_text_link_or_position);
        mMeetingDate = view.findViewById(R.id.text_view_date_meeting);

        mMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        mCreateMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                if(spinnerMeetingType.getSelectedItemId() == 1) {
                    type = "online";
                } else if(spinnerMeetingType.getSelectedItemId() == 2) {
                    type = "offline";
                }
                String info = mMeetingUrl.getText().toString();
                String date = mMeetingDate.getText().toString();
                Log.d("TAG", "onClick: " + date);

                if(!info.isEmpty() && !date.isEmpty() && !type.isEmpty()) {
                    mCreateMeetingViewModel.addMeeting(groupBundle.getId(), type, info, date);
                    requireActivity().onBackPressed();
                } else {
                    Snackbar.make(view, "Invalid Meeting", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;

        mMeetingDate.setText(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().findViewById(R.id.toolbar_text_view).setVisibility(View.VISIBLE);
    }
}