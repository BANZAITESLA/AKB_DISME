package com.disu.disme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

/** 03/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//      set animasi pada splash screen
        ConstraintLayout cons_layout = findViewById(R.id.splash_layout);
        AnimationDrawable ani_draw = (AnimationDrawable) cons_layout.getBackground();
        ani_draw.setEnterFadeDuration(10);
        ani_draw.setExitFadeDuration(1000);
        ani_draw.start();

//      set delay next activity pada splash screen dengan merubah transisi
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 6500);
    }
}