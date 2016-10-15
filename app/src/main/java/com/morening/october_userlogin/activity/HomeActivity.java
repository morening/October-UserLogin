package com.morening.october_userlogin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.morening.october_userlogin.R;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        final ImageView imageView = (ImageView) findViewById(R.id.id_home_imageview);

        Slide enterSlide = new Slide(Gravity.LEFT);
        enterSlide.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                final View rootView = getWindow().getDecorView();
                Animator anim = ViewAnimationUtils.createCircularReveal(rootView, (rootView.getLeft()+rootView.getRight())/2, (rootView.getTop()+rootView.getBottom())/2, 0, rootView.getHeight());
                anim.setDuration(1000);
                anim.setInterpolator(new LinearInterpolator());
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        rootView.setBackgroundColor(getResources().getColor(R.color.button_bg_color));
                    }
                });
                anim.start();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setEnterTransition(enterSlide);
    }
}
