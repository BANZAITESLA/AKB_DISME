package com.disu.disme;

import static com.disu.disme.AppDatabase.MIGRATION_1_2;
import static com.disu.disme.AppDatabase.MIGRATION_2_3;
import static com.disu.disme.AppDatabase.MIGRATION_3_4;
import static com.disu.disme.AppDatabase.MIGRATION_4_5;
import static com.disu.disme.AppDatabase.MIGRATION_5_6;
import static com.disu.disme.AppDatabase.MIGRATION_6_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * 09/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Button finish;
    Animation finish_up;
    Animation finish_down;

    OnboardingAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "disme")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7)
                .allowMainThreadQueries()
                .build();

        db.onboardingDao().insertAll(new OnboardingData(1, "What is DisMe?", "DisMe is an application that shows more about Myself haha", "img_onboard_1"));
        db.onboardingDao().insertAll(new OnboardingData(2, "How about the Data in DisMe?", "All information shown in this app is safe to share in public. Its means, that some are fake to keep personal data safe.", "img_onboard_2"));
        db.onboardingDao().insertAll(new OnboardingData(3, "Message from the creator,", "DisMe contains so much data stored in your cache. Some features need your permission to meet their requirements. I guarantee all is safe.. so enjoy!", "img_onboard_3"));

        List<OnboardingData> screen_item = db.onboardingDao().getAllData();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPagerAdapter = new OnboardingAdapter(this, screen_item);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(viewListener);

        finish = (Button) findViewById(R.id.button_right);
        finish_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        finish_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevData();
                Intent i = new Intent(OnboardingActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        if (restorePrevData()) {
            Intent i = new Intent(OnboardingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 2) {
                finish.setAnimation(finish_up);
                finish.setVisibility(View.VISIBLE);
            } else {
                finish.setAnimation(finish_down);
                finish.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void prevData() {
        SharedPreferences prev = getApplicationContext().getSharedPreferences("prev", MODE_PRIVATE);
        SharedPreferences.Editor editor = prev.edit();
        editor.putBoolean("opened",true);
        editor.commit();
    }

    private boolean restorePrevData() {
        SharedPreferences prev = getApplicationContext().getSharedPreferences("prev", MODE_PRIVATE);
        Boolean opened = prev.getBoolean("opened", false);
        return opened;
    }
}