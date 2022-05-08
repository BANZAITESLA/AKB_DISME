package com.disu.disme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<FriendListData> friend_list;

    public FriendAdapter(List<FriendListData> friend_list) {
        this.friend_list = friend_list;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        holder.nameFriend.setText(friend_list.get(position).getName());
        holder.usernameFriend.setText(friend_list.get(position).getUsername());

        Context context = holder.imageFriend.getContext();
        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        int id = context.getResources().getIdentifier(friend_list.get(position).getImage(), "drawable", context.getPackageName());
        Picasso.with(context).load(id).fit().centerCrop().into(holder.imageFriend, new Callback() {
            @Override
            public void onSuccess() {
                if (playAnimation.get()) {
                    Animation fadeOutTitle = new AlphaAnimation(0, 1);
                    fadeOutTitle.setInterpolator(new AccelerateInterpolator());
                    fadeOutTitle.setDuration(1000);
                    holder.imageFriend.startAnimation(fadeOutTitle);
                    holder.nameFriend.startAnimation(fadeOutTitle);
                    holder.usernameFriend.startAnimation(fadeOutTitle);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return friend_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageFriend;
        public TextView nameFriend;
        public TextView usernameFriend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFriend = itemView.findViewById(R.id.image_friend);
            nameFriend = itemView.findViewById(R.id.name_friend);
            usernameFriend = itemView.findViewById(R.id.username_friend);
        }
    }
}