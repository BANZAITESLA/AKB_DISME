package com.disu.disme;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    List<VideoData> video;

    public VideoAdapter(List<VideoData> video) {
        this.video = video;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row, parent, false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.titleVideo.setText(video.get(position).getTitle_media());
        holder.authorVideo.setText(video.get(position).getAuthor_media());

        Context context = holder.thumbnail.getContext();
        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        int id = context.getResources().getIdentifier(video.get(position).getThumbnail(), "drawable", context.getPackageName());
        Picasso.with(context).load(id).fit().centerCrop().into(holder.thumbnail, new Callback() {
            @Override
            public void onSuccess() {
                if (playAnimation.get()) {
                    Animation ltor = new TranslateAnimation(holder.thumbnail.getWidth(), 0, 0.0f, 0.0f);
                    ltor.setDuration(450);
                    ltor.setFillAfter(true);
                    holder.thumbnail.startAnimation(ltor);

                    Animation fadeOutTitle = new AlphaAnimation(0, 1);
                    fadeOutTitle.setInterpolator(new AccelerateInterpolator());
                    fadeOutTitle.setDuration(1000);
                    holder.titleVideo.startAnimation(fadeOutTitle);
                    holder.authorVideo.startAnimation(fadeOutTitle);
                }
            }

            @Override
            public void onError() {

            }
        });

        Context con_vid = holder.media.getContext();
        int id_video = con_vid.getResources().getIdentifier(video.get(position).getMedia(), "raw", con_vid.getPackageName());
        holder.media.setVideoPath("android.resource://" + con_vid.getPackageName()+"/"+id_video);
        MediaController mediaController = new MediaController(con_vid);
        mediaController.setAnchorView(holder.media);
        holder.media.setMediaController(mediaController);
        holder.media.requestFocus();
        holder.media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });

        holder.media.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Boolean isPlaying = holder.media.isPlaying();
                if (isPlaying == true) {
                    holder.layout_image.setVisibility(View.GONE);
                    holder.layout_text.setVisibility(View.GONE);
                } else {
                    holder.layout_image.setVisibility(View.VISIBLE);
                    holder.layout_text.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return video.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public VideoView media;
        public ImageView thumbnail;
        public TextView titleVideo;
        public TextView authorVideo;
        public View shadow;
        public LinearLayout layout_text;
        public RelativeLayout layout_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            titleVideo = itemView.findViewById(R.id.title_video);
            authorVideo = itemView.findViewById(R.id.author_video);
            media = itemView.findViewById(R.id.media_video);
            shadow = itemView.findViewById(R.id.shadow_video);
            layout_text = itemView.findViewById(R.id.layout_text);
            layout_image = itemView.findViewById(R.id.layout_image);
        }
    }
}
