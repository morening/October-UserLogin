package com.morening.october_userlogin.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morening.october_userlogin.R;

/**
 * Created by morening on 2016/10/9.
 */

public class AnimationImageButton extends FrameLayout {

    private final String TAG = "AnimationImageButton";

    private View mView = null;
    private TextView mTextView = null;
    private ImageView mRotateView = null;

    private RotateAnimation mRotateAnim = null;

    private int mPreviousWidth = 0;

    public AnimationImageButton(Context context) {
        this(context, null);
    }

    public AnimationImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        obtainStyledAtrrs(attrs);

        initViews();

    }

    private void initViews() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.animation_image_button_layout, this, false);
        LayoutParams view_lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mView, view_lp);

        mTextView = (TextView) mView.findViewById(R.id.id_animation_image_button_text_view);
        mRotateView = (ImageView) mView.findViewById(R.id.id_animation_image_button_rotate_view);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPreviousWidth = getWidth();
            }
        });
    }


    private void obtainStyledAtrrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AnimationImageButton);



        ta.recycle();
    }

    /*
     * Start button scale down animation if invoke this function.
     * And then show the progress
     */
    public void startScaleAnimation(){
        ValueAnimator valueAnim = ValueAnimator.ofInt(mPreviousWidth,
                getResources().getDimensionPixelSize(R.dimen.animation_image_button_height));
        valueAnim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTextView.setVisibility(GONE);
                mRotateView.setVisibility(VISIBLE);
                mRotateAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                mRotateAnim.setRepeatMode(Animation.RESTART);
                mRotateAnim.setRepeatCount(Animation.INFINITE);
                mRotateAnim.setDuration(1000);
                mRotateView.setAnimation(mRotateAnim);
                mRotateAnim.startNow();
            }
        });
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();
                lp.width = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        valueAnim.setDuration(500);
        valueAnim.setInterpolator(new LinearInterpolator());
        valueAnim.start();
    }

    public void startRecoveryAnimation(){
        ValueAnimator valueAnim = ValueAnimator.ofInt(
                getResources().getDimensionPixelSize(R.dimen.animation_image_button_height),
                mPreviousWidth);
        valueAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTextView.setVisibility(VISIBLE);
                setClickable(true);
            }
        });
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();
                lp.width = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        valueAnim.setDuration(500);
        valueAnim.setInterpolator(new LinearInterpolator());
        valueAnim.start();
    }

    /*
     * dismiss progress when need to stop the progress animation.
     */
    public void dismissProgress(){
        if (mRotateAnim != null){
            mRotateAnim.cancel();
        }
        mRotateView.setVisibility(GONE);
    }

    public View getSharedElement(){

        return this;
    }
}
