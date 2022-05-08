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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{

    List<MusicData> music;
    MediaPlayer mpr = new MediaPlayer();

    public MusicAdapter(List<MusicData> music) {
        this.music = music;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_row, parent, false);
        return new MusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
        holder.titleMusic.setText(music.get(position).getTitle_media());
        holder.authorMusic.setText(music.get(position).getAuthor_media());

        Context context = holder.imageMusic.getContext();
        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        int id = context.getResources().getIdentifier(music.get(position).getImage(), "drawable", context.getPackageName());
        Picasso.with(context).load(id).fit().centerCrop().into(holder.imageMusic, new Callback() {
            @Override
            public void onSuccess() {
                if (playAnimation.get()) {
                    //play fade
                    Animation ltor = new TranslateAnimation(holder.imageMusic.getWidth(), 0, 0.0f, 0.0f);
                    ltor.setDuration(450);
                    ltor.setFillAfter(true);
                    holder.imageMusic.startAnimation(ltor);

                    Animation fadeOutTitle = new AlphaAnimation(0, 1);
                    fadeOutTitle.setInterpolator(new AccelerateInterpolator());
                    fadeOutTitle.setDuration(1000);
                    holder.titleMusic.startAnimation(fadeOutTitle);

                    Animation fadeOutDesc = new AlphaAnimation(0, 1);
                    fadeOutDesc.setInterpolator(new AccelerateInterpolator());
                    fadeOutDesc.setDuration(1000);
                    holder.authorMusic.startAnimation(fadeOutDesc);
                }
            }

            @Override
            public void onError() {

            }
        });

        Context con_med = holder.buttonPlay.getContext();
        int id_music = con_med.getResources().getIdentifier(music.get(position).getMedia(), "raw", con_med.getPackageName());
        holder.buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (mpr.isPlaying()) {
                    mpr.stop();
                    mpr.reset();
                    holder.viewMusic.setBackgroundResource(R.drawable.ic_play);
                } else {
                    mpr = MediaPlayer.create(con_med, id_music);
                    mpr.start();
                    holder.viewMusic.setBackgroundResource(R.drawable.ic_stop);
                };
            }

        });

    }

    @Override
    public int getItemCount() {
        return music.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageMusic;
        public TextView titleMusic;
        public TextView authorMusic;
        public Button buttonPlay;
        public View viewMusic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMusic = itemView.findViewById(R.id.image_music);
            titleMusic = itemView.findViewById(R.id.title_music);
            authorMusic = itemView.findViewById(R.id.author_music);
            buttonPlay = itemView.findViewById(R.id.play_button_music);
            viewMusic = itemView.findViewById(R.id.play_icon_music);
        }
    }
}
