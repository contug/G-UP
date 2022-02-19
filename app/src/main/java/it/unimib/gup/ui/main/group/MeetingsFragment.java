package it.unimib.gup.ui.main.group;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import it.unimib.gup.R;
import it.unimib.gup.model.Meeting;
import it.unimib.gup.model.responses.GroupResponse;
import it.unimib.gup.viewmodels.GroupDetailsViewModel;

public class MeetingsFragment extends Fragment {

    private View mOnlineMeetingContainer;
    private TextView mOnlineMeetingDate;
    private TextView mOnlineMeetingInfo;

    private View mOfflineMeetingContainer;
    private TextView mOfflineMeetingDate;
    private TextView mOfflineMeetingInfo;

    private GroupDetailsViewModel mGroupDetailsViewModel;

    public MeetingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGroupDetailsViewModel = ViewModelProviders.of(requireActivity()).get(GroupDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meetings, container, false);

        mOnlineMeetingContainer = view.findViewById(R.id.meeting_online);
        mOnlineMeetingDate = view.findViewById(R.id.meeting_online_date);
        mOnlineMeetingInfo = view.findViewById(R.id.meeting_online_text);

        mOfflineMeetingContainer = view.findViewById(R.id.meeting_offline);
        mOfflineMeetingDate = view.findViewById(R.id.meeting_offline_date);
        mOfflineMeetingInfo = view.findViewById(R.id.meeting_offline_text);

        mGroupDetailsViewModel.getGroupNoFetch().observe(getViewLifecycleOwner(), new Observer<GroupResponse>() {
            @Override
            public void onChanged(GroupResponse groupResponse) {
                if(groupResponse.getGroup().getMeetings() != null) {
                    if(groupResponse.getGroup().getMeetings().get("online") != null) {
                        mOnlineMeetingContainer.setVisibility(View.VISIBLE);

                        mOnlineMeetingDate.setText(groupResponse.getGroup().getMeetings().get("online").getDate());
                        mOnlineMeetingInfo.setText(groupResponse.getGroup().getMeetings().get("online").getUrl());
                    } else {
                        mOnlineMeetingContainer.setVisibility(View.GONE);
                    }

                    if(groupResponse.getGroup().getMeetings().get("offline") != null) {
                        mOfflineMeetingContainer.setVisibility(View.VISIBLE);

                        mOfflineMeetingDate.setText(groupResponse.getGroup().getMeetings().get("offline").getDate());
                        mOfflineMeetingInfo.setText(groupResponse.getGroup().getMeetings().get("offline").getUrl());
                    } else {
                        mOfflineMeetingContainer.setVisibility(View.GONE);
                    }
                } else {
                    mOnlineMeetingContainer.setVisibility(View.GONE);
                    mOfflineMeetingContainer.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}