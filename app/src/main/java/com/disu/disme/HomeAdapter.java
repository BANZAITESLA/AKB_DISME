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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 08/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<HomeData> home;

    public HomeAdapter(List<HomeData> home) {
        this.home = home;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.textName.setText(home.get(position).getName());
        holder.descHome.setText(home.get(position).getDesc_int());

        final AtomicBoolean playAnimation = new AtomicBoolean(true);
        Context context = holder.imageHome.getContext();
        String homeImage = "img_home_1";
        int id = context.getResources().getIdentifier(home.get(position).getImage(), "drawable", context.getPackageName());
        if (id != context.getResources().getIdentifier(homeImage, "drawable", context.getPackageName())) {
            Picasso.with(context).load(id).into(holder.imageHome, new Callback() {
                @Override
                public void onSuccess() {
                    if (playAnimation.get()) {
                        //play fade
                        Animation ltor = new TranslateAnimation(holder.relativeLayout.getWidth(), 0, 0.0f, 0.0f);
                        ltor.setDuration(450);
                        ltor.setFillAfter(true);
                        holder.relativeLayout.startAnimation(ltor);

                        Animation fadeOutTitle = new AlphaAnimation(0, 1);
                        fadeOutTitle.setInterpolator(new AccelerateInterpolator());
                        fadeOutTitle.setDuration(1000);
                        holder.circleImage.startAnimation(fadeOutTitle);
                    }
                }

                @Override
                public void onError() {

                }
            });
            holder.imageHome.setVisibility(View.VISIBLE);
        } else {
            holder.imageHome.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return home.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageHome;
        public CircleImageView circleImage;
        public TextView textName;
        public TextView descHome;
        public RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageHome = itemView.findViewById(R.id.image_home);
            textName = itemView.findViewById(R.id.name_interest);
            descHome = itemView.findViewById(R.id.desc_interest);
            circleImage = itemView.findViewById(R.id.circle_interest);
            relativeLayout = itemView.findViewById(R.id.right_layout);
        }
    }
}
