package it.unimib.gup.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
;
import java.util.List;

import it.unimib.gup.R;
import it.unimib.gup.model.HomePost;

public class HomePostsRecyclerViewAdapter extends RecyclerView.Adapter<HomePostsRecyclerViewAdapter.HomePostsListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(HomePost post);
    }

    private List<HomePost> mPostList;

    private final OnItemClickListener mOnItemClickListener;

    public HomePostsRecyclerViewAdapter(List<HomePost> postList, OnItemClickListener onItemClickListener) {
        this.mPostList = postList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HomePostsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post_recycler_view_item, parent, false);

        return new HomePostsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePostsListViewHolder holder, int position) {
        holder.bind(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPostList != null) {
            return mPostList.size();
        }

        return 0;
    }

    public class HomePostsListViewHolder extends RecyclerView.ViewHolder {

        private final View postContainer;
        private final View circle;
        // private final TextView groupId;
        private final TextView groupName;
        private final TextView author;
        private final TextView text;
        // private final TextView date;

        public HomePostsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.postContainer = itemView.findViewById(R.id.home_post_recycler_view_item);
            this.circle = itemView.findViewById(R.id.home_post_group_circle);
            this.groupName = itemView.findViewById(R.id.home_post_group_name);
            this.author = itemView.findViewById(R.id.home_post_author);
            this.text = itemView.findViewById(R.id.home_post_text);
        }

        public void bind(HomePost post) {
            this.circle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(post.getGroupColor())));
            this.groupName.setText(post.getGroupName());
            this.author.setText(post.getAuthor());
            this.text.setText(post.getText());


            this.postContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(post);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(post);
                }
            });
        }
    }
}