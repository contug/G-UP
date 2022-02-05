package it.unimib.gup.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.lang.reflect.Field;

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

        Context context = getContext();

        String name = mFieldOfStudy[position].getName();

        ((TextView) convertView.findViewById(R.id.setting_field_of_study_name))
                .setText(name);


        String color = mFieldOfStudy[position].getColor();

        ((CardView)convertView.findViewById(R.id.setting_field_of_study_option))
                .setBackgroundColor(Color.parseColor(color));


        return convertView;
    }
}
