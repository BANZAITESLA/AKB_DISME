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

/**
 * 09/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class OnboardingAdapter extends PagerAdapter {

    Context context;

    int image[] = {
            R.drawable.img_onboard_1,
            R.drawable.img_onboard_2,
            R.drawable.img_onboard_3
    };

    int string[] = {
            R.string.desc_app_1,
            R.string.desc_app_2,
            R.string.desc_app_3
    };

    public OnboardingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboard_1, container, false);

        ImageView onboardImage = (ImageView) view.findViewById(R.id.image_onboard);
        TextView onboardText = (TextView) view.findViewById(R.id.text_onboard);

        onboardImage.setImageResource(image[position]);
        onboardText.setText(string[position]);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
