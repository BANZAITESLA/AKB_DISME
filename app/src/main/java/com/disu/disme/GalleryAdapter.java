package com.disu.disme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    List<GalleryData> gallery;

    public GalleryAdapter(List<GalleryData> gallery) {
        this.gallery = gallery;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_row, parent, false);
        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {

        Context context = holder.imageGallery.getContext();
        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        int id = context.getResources().getIdentifier(gallery.get(position).getImage(), "drawable", context.getPackageName());
        Picasso.with(context).load(id).into(holder.imageGallery, new Callback() {
            @Override
            public void onSuccess() {
                if (playAnimation.get()) {
                    Animation ltor = new TranslateAnimation(holder.imageGallery.getWidth(), 0, 0.0f, 0.0f);
                    ltor.setDuration(450);
                    ltor.setFillAfter(true);
                    holder.imageGallery.startAnimation(ltor);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public GridViewItem imageGallery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageGallery = itemView.findViewById(R.id.grid_view);
        }
    }
}
