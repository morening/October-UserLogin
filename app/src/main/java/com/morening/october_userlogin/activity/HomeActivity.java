package com.morening.october_userlogin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Toolbar;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.morening.october_userlogin.R;
import com.morening.october_userlogin.adapter.HomeSlidingMenuAdapter;
import com.morening.october_userlogin.model.HomeSlidingMenuModule;

public class HomeActivity extends Activity {

    private Toolbar mToolbar = null;
    private View mToolbarLayout = null;
    private SlidingMenu mSlidingMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_home);

        setupTransition();
        setupViews();
        setupSlidingMenu();
    }

    private void setupSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setBehindOffsetRes(R.dimen.home_slidingmenu_behind_offset);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                canvas.scale(percentOpen, percentOpen, 0, canvas.getHeight()/2);
            }
        });
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setShadowWidthRes(R.dimen.home_slidingmenu_shadow_width);
        mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow_bg);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        setupMenuItems();
    }

    private void setupMenuItems() {
        View slidingmenu = LayoutInflater.from(this).inflate(R.layout.slidingmenu_left_menu_layout, null);
        RecyclerView recyclerView = (RecyclerView) slidingmenu.findViewById(R.id.id_slidingmenu_left_menu_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new HomeSlidingMenuAdapter(this, HomeSlidingMenuModule.getMenuItems()));


        mSlidingMenu.setMenu(slidingmenu);
    }

    private void setupTransition() {
        Explode explode = new Explode();
        explode.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Animator anim = ViewAnimationUtils.createCircularReveal(mToolbarLayout, (mToolbarLayout.getLeft()+mToolbarLayout.getRight())/2, (mToolbarLayout.getTop()+mToolbarLayout.getBottom())/2, 0, mToolbarLayout.getWidth());
                anim.setDuration(500);
                anim.setInterpolator(new LinearInterpolator());
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mToolbarLayout.setBackgroundColor(getResources().getColor(R.color.button_bg_color));
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        View sharedView = findViewById(R.id.id_home_shared_toolbar);
                        sharedView.setVisibility(View.GONE);
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
        getWindow().setEnterTransition(explode);
    }

    private void setupViews() {
        mToolbar = (Toolbar) findViewById(R.id.id_profile_toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayShowTitleEnabled(false);

        mToolbarLayout = findViewById(R.id.id_home_toolbar_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
