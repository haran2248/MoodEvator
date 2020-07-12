package com.example.moodevator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.PostVH> {
    @NonNull
    ArrayList<Post> postArrayList;
    Context context;

    public PostRVAdapter(@NonNull ArrayList<Post> postArrayList, Context context) {
        this.postArrayList = postArrayList;
        this.context = context;
    }

    public PostRVAdapter.PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostRVAdapter.PostVH holder, int position) {
        holder.populate(postArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class PostVH extends RecyclerView.ViewHolder {
        ImageView postImg;
        TextView caption,name;
        public PostVH(@NonNull View itemView) {
            super(itemView);
            postImg=itemView.findViewById(R.id.imgPost);
            caption=itemView.findViewById(R.id.caption_post);
            name=itemView.findViewById(R.id.name_post);
        }

        public void populate(Post post) {
            caption.setText(post.getCaption());
            name.setText(post.getName());
            Glide.with(context).load(post.getImguri()).into(postImg);
        }
    }
}
