package it.unimib.gup.adapter;

import android.content.res.ColorStateList;
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

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
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
    private final OnItemClickListener mOnSubscribeClickListener;


    public BrowseGroupsRecyclerViewAdapter(List<Group> groupList, OnItemClickListener onItemClickListener, OnItemClickListener OnSubscribeClickListener) {
        this.mGroupList = groupList;
        this.mGroupListAll = groupList;
        this.mOnItemClickListener = onItemClickListener;
        this.mOnSubscribeClickListener = OnSubscribeClickListener;
    }

    public void setGroupListAll(List<Group> groupListAll) {
        this.mGroupListAll = groupListAll;
    }

    public int setFilteredList(String text) {
        List<Group> filteredList = new ArrayList<>();

        mGroupList.clear();
        for (Group group : mGroupListAll) {
            if (group.getName().toLowerCase().startsWith(text.toLowerCase()) || group.getCategory().getName().toLowerCase().startsWith(text.toLowerCase()) ) {
                filteredList.add(group);
            }
        }

        mGroupList.addAll(filteredList);
        notifyDataSetChanged();

        return filteredList.size();
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

            String uId = FirebaseAuth.getInstance().getUid();
            HashMap<String, String> userList = group.getMembers();
            if (userList != null) {
                if(userList.containsKey(uId)){
                    Button recyclerViewItemButton = itemView.findViewById(R.id.browse_groups_subscribe_button);
                    recyclerViewItemButton.setVisibility(View.GONE);
                }
            }

            this.subscribeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSubscribeClickListener.onItemClick(group);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uId = FirebaseAuth.getInstance().getUid();
                    HashMap<String, String> userList = group.getMembers();
                    if (userList != null) {
                        if(userList.containsKey(uId)){
                            mOnItemClickListener.onItemClick(group);
                        }
                    }
                }
            });
        }
    }
}