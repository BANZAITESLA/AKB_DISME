package com.disu.disme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 09/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout linearLayout;
    Button next;

    TextView[] dots;
    OnboardingAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        next = (Button) findViewById(R.id.button_right);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnboardingActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        viewPagerAdapter = new OnboardingAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpIndicator(int position) {
        dots = new TextView[3];
        linearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.base45, getApplicationContext().getTheme()));
            linearLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.base, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

            if (position == 2) {
                next.setVisibility(View.VISIBLE);
            } else {
                next.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i) {
        return viewPager.getCurrentItem();
    }
}