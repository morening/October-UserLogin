package com.morening.october_userlogin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.morening.october_userlogin.R;

/**
 * Created by morening on 2016/10/9.
 */

public class AnimationImageButton extends FrameLayout {

    private View mView = null;
    private ImageView mButtonView = null;
    private TextView mTextView = null;
    private ImageView mRotateView = null;

    private RotateAnimation mRotateAnim = null;
    private boolean bIsRotAnimPlaying = false;

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

        mButtonView = (ImageView) mView.findViewById(R.id.id_animation_image_button_button_view);
        mTextView = (TextView) mView.findViewById(R.id.id_animation_image_button_text_view);
        mRotateView = (ImageView) mView.findViewById(R.id.id_animation_image_button_rotate_view);
    }

    private void obtainStyledAtrrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AnimationImageButton);



        ta.recycle();
    }

    public void startAnimation(){

        bIsRotAnimPlaying = false;

        ValueAnimator valueAnim = ValueAnimator.ofInt(getWidth(),
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

                mRotateAnim = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                mRotateAnim.setRepeatCount(10);
                mRotateAnim.setDuration(1000);
                mRotateView.setAnimation(mRotateAnim);
                mRotateAnim.startNow();
                bIsRotAnimPlaying = true;
            }
        });
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams lp = mButtonView.getLayoutParams();
                lp.width = (int) animation.getAnimatedValue();
                mButtonView.requestLayout();
            }
        });
        valueAnim.setDuration(1000);
        valueAnim.setInterpolator(new LinearInterpolator());
        valueAnim.start();
    }

    public void cancelAnimation(OnCancelAnimationCallback callback){
        if (mRotateAnim != null && bIsRotAnimPlaying){
            mRotateAnim.cancel();
            mRotateView.setVisibility(GONE);
            bIsRotAnimPlaying = false;
            callback.onCancel();
        }
    }

    public interface OnCancelAnimationCallback{
        void onCancel();
    }

}
