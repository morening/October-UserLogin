package com.morening.october_userlogin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.morening.october_userlogin.R;

public class HomeActivity extends Activity {

    private Toolbar mToolbar = null;
    private View mTopView = null;
    private ImageView mSharedElement = null;
    private ImageView mUserPhoto = null;

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
        downloadUserPhoto();
    }

    private void downloadUserPhoto() {

        // default user photo
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.home_page_user_photo_sample);
        Bitmap photo = createCycleImage(source, getResources().getDimensionPixelSize(R.dimen.home_page_user_photo_min_size));
        mUserPhoto.setImageBitmap(photo);

        // download from network

    }

    private Bitmap createCycleImage(Bitmap source, int min) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min/2, min/2, min/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);

        return target;
    }

    private void setupTransition() {
        Slide enterSlide = new Slide(Gravity.RIGHT);
        enterSlide.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Animator anim = ViewAnimationUtils.createCircularReveal(mTopView, (mTopView.getLeft()+mTopView.getRight())/2, (mTopView.getTop()+mTopView.getBottom())/2, 0, mTopView.getWidth());
                anim.setDuration(500);
                anim.setInterpolator(new LinearInterpolator());
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mTopView.setBackgroundColor(getResources().getColor(R.color.button_bg_color));
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mSharedElement.setVisibility(View.GONE);
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

    private void setupViews() {
        mToolbar = (Toolbar) findViewById(R.id.id_home_page_toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayShowTitleEnabled(false);

        mTopView = findViewById(R.id.id_home_page_top_layout);
        mSharedElement = (ImageView) findViewById(R.id.id_home_page_shared_element);
        mUserPhoto = (ImageView) findViewById(R.id.id_home_page_user_photo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
