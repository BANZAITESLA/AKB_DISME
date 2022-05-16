package com.disu.disme;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/** 04/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      set default fragment
        loadFragment(new HomeFragment());
        loadFragment(new ProfileFragment());
        loadFragment(new GalleryFragment());
        loadFragment(new DailyActivityFragment());
        loadFragment(new MusicVideoFragment());
        BottomNavigationView bottom_nav = findViewById(R.id.bottomNavigationView);
        bottom_nav.setOnItemSelectedListener(this);
        bottom_nav.setSelectedItemId(R.id.menu_home);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.fade_out  // popExit
                    )
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame_layout);
        if (fl.getChildCount() >= 0) {
            super.onBackPressed();
            finishAffinity();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_daily:
                fragment = new DailyActivityFragment();
                break;
            case R.id.menu_music_video:
                fragment = new MusicVideoFragment();
                break;
            case R.id.menu_gallery:
                fragment = new GalleryFragment();
                break;
            case R.id.menu_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }
}