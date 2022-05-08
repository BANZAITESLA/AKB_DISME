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

/**
 * 05/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    List<DailyActivityData> daily_activity;

    public DailyAdapter(List<DailyActivityData> daily_activity) {
        this.daily_activity = daily_activity;
    }

    @NonNull
    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_activity_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyAdapter.ViewHolder holder, int position) {
        holder.titleDaily.setText(daily_activity.get(position).getTitle());
        holder.descDaily.setText(daily_activity.get(position).getDesc());

        Context context = holder.imageDaily.getContext();
        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        int id = context.getResources().getIdentifier(daily_activity.get(position).getImage(), "drawable", context.getPackageName());
        Picasso.with(context).load(id).fit().centerCrop().into(holder.imageDaily, new Callback() {
            @Override
            public void onSuccess() {
                if (playAnimation.get()) {
                    Animation ltor = new TranslateAnimation(holder.imageDaily.getWidth(), 0, 0.0f, 0.0f);
                    ltor.setDuration(450);
                    ltor.setFillAfter(true);
                    holder.imageDaily.startAnimation(ltor);

                    Animation fadeOutTitle = new AlphaAnimation(0, 1);
                    fadeOutTitle.setInterpolator(new AccelerateInterpolator());
                    fadeOutTitle.setDuration(1000);
                    holder.titleDaily.startAnimation(fadeOutTitle);
                    holder.descDaily.startAnimation(fadeOutTitle);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return daily_activity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageDaily;
        public TextView titleDaily;
        public TextView descDaily;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDaily = itemView.findViewById(R.id.image_daily);
            titleDaily = itemView.findViewById(R.id.title_daily);
            descDaily = itemView.findViewById(R.id.desc_daily);
        }
    }
}