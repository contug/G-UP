package it.unimib.gup.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.unimib.gup.R;
import it.unimib.gup.model.Post;
import it.unimib.gup.repository.groups.Repository;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.NotesListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    private List<Post> mPosts;
    private final OnItemClickListener mOnItemClickListener;


    public PostsRecyclerViewAdapter(List<Post> posts, OnItemClickListener onItemClickListener) {
        this.mPosts = posts;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NotesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_recycler_view_item, parent, false);

        return new NotesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPosts != null) {
            return mPosts.size();
        }

        return 0;
    }

    public class NotesListViewHolder extends RecyclerView.ViewHolder {

        private final TextView author;
        private final TextView text;
        private final TextView date;

        public NotesListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.author = itemView.findViewById(R.id.post_author);
            this.text = itemView.findViewById(R.id.post_text);
            this.date = itemView.findViewById(R.id.post_date);
        }

        public void bind(Post post) {
            this.author.setText(post.getAuthor());
            this.text.setText(post.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ITALY);
            this.date.setText(dateFormat.format(new Date(post.getData())));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(post);
                }
            });
        }
    }
}