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

public class BrowseGroupsRecyclerViewAdapter extends RecyclerView.Adapter<BrowseGroupsRecyclerViewAdapter.BrowseGroupsListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Group group);
    }

    private List<Group> mGroupList;
    private List<Group> mGroupListAll;
    private final OnItemClickListener mOnItemClickListener;

    public BrowseGroupsRecyclerViewAdapter(List<Group> groupList, OnItemClickListener onItemClickListener) {
        this.mGroupList = groupList;
        mGroupListAll = new ArrayList<>(mGroupList);
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setFilteredList(String text) {
        List<Group> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(mGroupListAll);
        } else {
            for (Group group : mGroupListAll) {
                if (group.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(group);
                }
            }
        }

        if (!filteredList.isEmpty()) {
            mGroupList = filteredList;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BrowseGroupsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_groups_recycler_view_item, parent, false);

        return new BrowseGroupsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseGroupsListViewHolder holder, int position) {
        holder.bind(mGroupList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mGroupList != null) {
            return mGroupList.size();
        }

        return 0;
    }

    public class BrowseGroupsListViewHolder extends RecyclerView.ViewHolder {

        private final View circle;
        private final TextView name;
        private final TextView description;
        private final TextView category;
        private final FrameLayout categoryContainer;
        private final Button subscribeButton;

        public BrowseGroupsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.circle = itemView.findViewById(R.id.browse_groups_group_circle);
            this.name = itemView.findViewById(R.id.browse_groups_group_name);
            this.description = itemView.findViewById(R.id.browse_groups_description);
            this.category = itemView.findViewById(R.id.browse_groups_category_text);
            this.categoryContainer = itemView.findViewById(R.id.browse_groups_category_container);
            this.subscribeButton = itemView.findViewById(R.id.browse_groups_subscribe_button);
        }

        public void bind(Group group) {
            this.circle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getColor())));
            this.name.setText(group.getName());
            this.category.setText(group.getCategory().getName());
            this.description.setText(group.getDescription());
            this.categoryContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(group.getCategory().getColor())));

            this.subscribeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(group);
                }
            });
        }
    }
}