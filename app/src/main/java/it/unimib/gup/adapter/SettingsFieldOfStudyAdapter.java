package it.unimib.gup.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.unimib.gup.R;
import it.unimib.gup.ui.settings.FieldOfStudy;

public class SettingsFieldOfStudyAdapter extends ArrayAdapter<FieldOfStudy>{
    private final String TAG = "SETTINGS_FIELD_OF_STUDY_ADAPTER";

    private FieldOfStudy[] mFieldOfStudy;

    public SettingsFieldOfStudyAdapter(@NonNull Context context, int resource, @NonNull FieldOfStudy[] objects) {
        super(context, resource, objects);
        mFieldOfStudy = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_field_of_study_option, parent,false);
        }

        Log.d(TAG, mFieldOfStudy[position].toString());

        ((TextView) convertView.findViewById(R.id.setting_field_of_study_name))
                .setText(mFieldOfStudy[position].getName());

//        ((TextView) convertView.findViewById(R.id.setting_field_of_study_name))
//                .setBackground(mFieldOfStudy[position].getName());

        return convertView;
    }
}
