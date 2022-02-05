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

    private FieldOfStudy[] mFieldOfStudies;

    public SettingsFieldOfStudyAdapter(@NonNull Context context, int resource, @NonNull FieldOfStudy[] objects) {
        super(context, resource, objects);
        mFieldOfStudies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_field_of_study_option, parent,false);
        }
        FieldOfStudy mFieldOfStudy = mFieldOfStudies[position];

        Context context = getContext();

        String name = mFieldOfStudy.getName();
        TextView mLabel = (TextView) convertView.findViewById(R.id.setting_field_of_study_name);
        mLabel.setText(name);

        String color = mFieldOfStudy.getColor();
        CardView mListItem = (CardView)convertView.findViewById(R.id.setting_field_of_study_option);
        mListItem.setCardBackgroundColor(Color.parseColor(color));


        mListItem.setOnClickListener(v -> {
            mFieldOfStudy.setSelected(!mFieldOfStudy.getSelected());

            if(mFieldOfStudy.getSelected()) {
                String newColor =  color.charAt(0) + "80" + color.substring(1);
                mListItem.setCardBackgroundColor(Color.parseColor(newColor));
            } else {
                mListItem.setCardBackgroundColor(Color.parseColor(color));
            }

            Log.d(TAG, "Clicked " + name);
        });


        return convertView;
    }
}
