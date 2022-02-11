package it.unimib.gup.adapter;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;

public class HomeGroupsRecyclerViewAdapter extends RecyclerView.Adapter<HomeGroupsRecyclerViewAdapter.HomeGroupsListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Group group);
    }

    private List<Group> mGroupList;

    private final OnItemClickListener mOnItemClickListener;

    public HomeGroupsRecyclerViewAdapter(List<Group> groupList, OnItemClickListener onItemClickListener) {
        this.mGroupList = groupList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HomeGroupsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_groups_recycler_view_item, parent, false);

        return new HomeGroupsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGroupsListViewHolder holder, int position) {
        holder.bind(mGroupList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mGroupList != null) {
            return mGroupList.size();
        }

        return 0;
    }

    public class HomeGroupsListViewHolder extends RecyclerView.ViewHolder {

        private final View groupContainer;
        private final View circle;
        private final TextView name;
        private final TextView description;
        private final TextView category;
        private final FrameLayout categoryContainer;

        public HomeGroupsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.circle = itemView.findViewById(R.id.home_groups_group_circle);
            this.name = itemView.findViewById(R.id.home_groups_group_name);
            this.description = itemView.findViewById(R.id.home_groups_description);
            this.category = itemView.findViewById(R.id.home_groups_category_text);
            this.categoryContainer = itemView.findViewById(R.id.home_groups_category_container);
            this.groupContainer = itemView.findViewById(R.id.home_groups_recycler_view_item);
        }

        public void bind(Group group) {
            this.circle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getColor())));
            this.name.setText(group.getName());
            this.category.setText(group.getCategory().getName());
            this.description.setText(group.getDescription());
            this.categoryContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getCategory().getColor())));

            this.groupContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(group);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(group);
                }
            });
        }
    }
}