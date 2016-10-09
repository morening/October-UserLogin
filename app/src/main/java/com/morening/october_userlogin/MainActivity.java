package com.morening.october_userlogin;

import android.app.Activity;
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
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

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
