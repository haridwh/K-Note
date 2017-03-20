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
import com.skday.k_note.fragment.Intro;
import com.skday.k_note.fragment.IntroOne;
import com.skday.k_note.fragment.IntroTwo;
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

        addSlide(new Intro());
        addSlide(new IntroOne());
        addSlide(new IntroTwo());

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#66BB6A"));
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
        prefIntro.setFirstTimeLaunch(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#66BB6A"));
        }
    }
}