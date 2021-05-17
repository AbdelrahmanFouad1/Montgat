package com.abdo.montgat.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.introSlider.MainActivity;
import com.abdo.montgat.utlis.Constant;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SECREEN = 1000;

    private ImageView image;
    private TextView text1, text2;
    //variables
    private Animation topAnim, bottomAnim;

    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNightModeActivated();
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);



        image = findViewById(R.id.image_splash);
        text1 = findViewById(R.id.nameApp_splash);
        text2 = findViewById(R.id.desc_splash);

        image.setAnimation(topAnim);
        text1.setAnimation(bottomAnim);
        text2.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SECREEN);

    }

    public void checkNightModeActivated() {
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}