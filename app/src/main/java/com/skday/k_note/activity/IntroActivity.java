package com.skday.k_note.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.skday.k_note.R;
import com.skday.k_note.prefs.PrefIntro;

public class IntroActivity extends AppIntro {

    private PrefIntro prefIntro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefIntro = new PrefIntro(this);
        if (!prefIntro.isFirstTimeLaunch()) {
            launchHomeScreen();
        }

        changeStatusBarColor();
        addSlide(AppIntroFragment.newInstance("Welcome!", "This is a demo of the AppIntro library, with permissions being requested on a slide..", R.drawable.ic_slide1, Color.parseColor("#20d2bb")));
        addSlide(AppIntroFragment.newInstance("Permission Request", "In order to access your camera, you must give permissions.", R.drawable.ic_slide2, Color.parseColor("#20d2bb")));
        addSlide(AppIntroFragment.newInstance("Simple, yet Customizable", "The library offers a lot of customization, while keeping it simple for those that like simple.", R.drawable.ic_slide3, Color.parseColor("#20d2bb")));
        addSlide(AppIntroFragment.newInstance("Explore", "Feel free to explore the rest of the library demo!", R.drawable.ic_slide4, Color.parseColor("#20d2bb")));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setProgressIndicator();
        setBarColor(Color.parseColor("#20d2bb"));
        setSeparatorColor(Color.TRANSPARENT);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        launchHomeScreen();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchHomeScreen();
    }

    private void launchHomeScreen() {
        prefIntro.setFirstTimeLaunch(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#20d2bb"));
        }
    }
}