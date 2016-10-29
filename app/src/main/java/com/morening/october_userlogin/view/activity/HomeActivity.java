package com.morening.october_userlogin.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.morening.october_userlogin.MainActivity;
import com.morening.october_userlogin.R;
import com.morening.october_userlogin.view.adapter.HomeSlidingMenuAdapter;
import com.morening.october_userlogin.view.model.HomeSlidingMenuModule;

public class HomeActivity extends Activity implements View.OnClickListener{

    private Toolbar mToolbar = null;
    private View mToolbarLayout = null;
    private SlidingMenu mSlidingMenu = null;
    private ImageView mMenuBtn = null;
    public HomeScenarioManager mScenMgr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_home);

        mScenMgr = new HomeScenarioManager(this);

        setupTransition();
        setupViews();
        setupToolbar();
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
        mSlidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
                mMenuBtn.setVisibility(View.GONE);

                TextView titleText = (TextView) mToolbar.findViewById(R.id.id_home_toolbar_title);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) titleText.getLayoutParams();
                lp.gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
                titleText.requestLayout();
            }
        });
        mSlidingMenu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {
                mMenuBtn.setVisibility(View.VISIBLE);

                TextView titleText = (TextView) mToolbar.findViewById(R.id.id_home_toolbar_title);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) titleText.getLayoutParams();
                lp.gravity = Gravity.CENTER;
                titleText.requestLayout();

                titleText.setText(mScenMgr.getCurrentPageName());
            }
        });

        setupMenuItems();
    }

    private void setupMenuItems() {
        View slidingmenu = LayoutInflater.from(this).inflate(R.layout.slidingmenu_menu_layout, null);
        RecyclerView recyclerView = (RecyclerView) slidingmenu.findViewById(R.id.id_slidingmenu_menu_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(
                new HomeSlidingMenuAdapter(this, HomeSlidingMenuModule.getMenuItems()));
        mSlidingMenu.setMenu(slidingmenu);

        ImageView photoImage = (ImageView) slidingmenu.findViewById(R.id.id_slidingmenu_menu_photo);
        photoImage.setOnClickListener(this);

        View logoutBtn = slidingmenu.findViewById(R.id.id_slidingmenu_logout_btn);
        logoutBtn.setOnClickListener(this);
    }

    public void hideSlidingMenu(){
        if (mSlidingMenu != null && mSlidingMenu.isMenuShowing()){
            mSlidingMenu.showContent();
        }
    }

    public void showSlidingMenu(){
        if (mSlidingMenu != null && !mSlidingMenu.isMenuShowing()){
            mSlidingMenu.showMenu();
        }
    }

    private void setupTransition() {
        Explode explode = new Explode();
        explode.setDuration(400);
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
        mScenMgr.goNextPage(HomeScenarioManager.HOME_HOME_PAGE);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.id_home_toolbar);
        setActionBar(mToolbar);
        mMenuBtn = (ImageView) mToolbar.findViewById(R.id.id_home_toolbar_menu_btn);
        mMenuBtn.setOnClickListener(this);
        TextView titleText = (TextView) mToolbar.findViewById(R.id.id_home_toolbar_title);
        titleText.setText(mScenMgr.getCurrentPageName());

        mToolbarLayout = findViewById(R.id.id_home_toolbar_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_slidingmenu_menu_photo:
                mScenMgr.goNextPage(HomeScenarioManager.HOME_PROFILE_PAGE);
                hideSlidingMenu();
                break;
            case R.id.id_home_toolbar_menu_btn:
                showSlidingMenu();
                break;
            case R.id.id_slidingmenu_logout_btn:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    public View getToolbarLayout(){
        return mToolbarLayout;
    }
}
