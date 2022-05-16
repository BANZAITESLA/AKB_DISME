package com.disu.disme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 09/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class OnboardingAdapter extends PagerAdapter {

    Context context;
    List<OnboardingData> screen_item;

    public OnboardingAdapter(Context context, List<OnboardingData> screen_item) {
        this.context = context;
        this.screen_item = screen_item;
    }

    @Override
    public int getCount() {
        return screen_item.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboard_row,null);

        ImageView onboardImage = (ImageView) view.findViewById(R.id.image_onboard);
        TextView onboardTitle = (TextView) view.findViewById(R.id.title_onboard);
        TextView onboardDesc = (TextView) view.findViewById(R.id.desc_onboard);

        onboardTitle.setText(screen_item.get(position).getTitle());
        onboardDesc.setText(screen_item.get(position).getDesc());

        Context context_img = onboardImage.getContext();
        int id = context_img.getResources().getIdentifier(screen_item.get(position).getImage(),"drawable",context_img.getPackageName());
        Picasso.with(context_img).load(id).fit().centerCrop().into(onboardImage);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
