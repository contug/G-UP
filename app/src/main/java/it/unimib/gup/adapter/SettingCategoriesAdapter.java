package it.unimib.gup.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import it.unimib.gup.R;
import it.unimib.gup.ui.settings.CategoryItems;

public class SettingCategoriesAdapter extends ArrayAdapter<CategoryItems>{
    private final String TAG = "SETTINGS_FIELD_OF_STUDY_ADAPTER";

    private CategoryItems[] mCategoriesItems;

    public SettingCategoriesAdapter(@NonNull Context context, int resource, @NonNull CategoryItems[] objects) {
        super(context, resource, objects);
        mCategoriesItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_category_option, parent,false);
        }
        CategoryItems mCategoryItems = mCategoriesItems[position];

        Context context = getContext();

        String name = mCategoryItems.getName();
        TextView mLabel = (TextView) convertView.findViewById(R.id.setting_category_name);
        mLabel.setText(name);

        String color = mCategoryItems.getColor();
        CardView mListItem = (CardView)convertView.findViewById(R.id.setting_category_option);
        mListItem.setCardBackgroundColor(Color.parseColor(color));


        mListItem.setOnClickListener(v -> {
            mCategoryItems.setSelected(!mCategoryItems.getSelected());

            if(mCategoryItems.getSelected()) {
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
