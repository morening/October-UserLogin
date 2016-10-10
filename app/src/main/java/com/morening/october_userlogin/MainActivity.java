package com.morening.october_userlogin;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.morening.october_userlogin.view.AnimationImageButton;

public class MainActivity extends Activity {

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getActionBar().hide();

        final AnimationImageButton imageButton =
                (AnimationImageButton) findViewById(R.id.id_user_login_animation_button_login);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.startAnimation();

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButton.cancelAnimation(new AnimationImageButton.OnCancelAnimationCallback() {
                            @Override
                            public void onCancel() {

                            }
                        });
                    }
                }, 5000);
            }
        });
    }
}
