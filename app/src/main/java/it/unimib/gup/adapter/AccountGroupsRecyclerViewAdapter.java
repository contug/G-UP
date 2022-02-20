package it.unimib.gup.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.model.Group;

public class AccountGroupsRecyclerViewAdapter extends RecyclerView.Adapter<AccountGroupsRecyclerViewAdapter.BrowseGroupsListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Group group);
    }

    private List<Group> mGroupList;
    private final OnItemClickListener mOnItemClickListener;


    public AccountGroupsRecyclerViewAdapter(List<Group> groupList, OnItemClickListener onItemClickListener) {
        this.mGroupList = groupList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BrowseGroupsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_recycler_view_item, parent, false);

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

        private final ImageView image;
        private final TextView name;


        public BrowseGroupsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.account_group_image);
            this.name = itemView.findViewById(R.id.account_group_name);



        }

        public void bind(Group group) {
            this.name.setText(group.getName());
            Glide.with(itemView).load(group.getImageUrl()).
                    into(image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(group);
                }
            });
        }
    }
}