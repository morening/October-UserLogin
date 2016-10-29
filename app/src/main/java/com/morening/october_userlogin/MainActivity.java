package com.morening.october_userlogin;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.morening.october_userlogin.presenter.ILoginPresenter;
import com.morening.october_userlogin.presenter.impl.LoginPresenter;
import com.morening.october_userlogin.view.ILoginView;
import com.morening.october_userlogin.view.activity.HomeActivity;
import com.morening.october_userlogin.view.custom.AnimationImageButton;
import com.morening.october_userlogin.view.custom.SimpleEditView;

public class MainActivity extends Activity implements ILoginView{

    private AnimationImageButton mImageButton = null;
    private SimpleEditView mSimpleEditName = null;
    private SimpleEditView mSimpleEditPassword = null;

    private ILoginPresenter mLoginPresenter = null;

    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        mLoginPresenter = new LoginPresenter(this);
        mHandler = new Handler(Looper.getMainLooper());

        setupTransition();
        setupViews();
    }

    private void setupTransition() {
        Explode enterExp = new Explode();
        getWindow().setReturnTransition(enterExp);
    }

    private void setupViews() {
        mImageButton = (AnimationImageButton) findViewById(R.id.id_user_login_animation_button_login);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login();
            }
        });

        mSimpleEditName = (SimpleEditView) findViewById(R.id.id_user_login_simple_edit_name);
        mSimpleEditPassword = (SimpleEditView) findViewById(R.id.id_user_login_simple_edit_password);
        mSimpleEditName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mSimpleEditPassword.requestFocus();
                    mSimpleEditPassword.setSelection(mSimpleEditPassword.getCount());
                }

                return false;
            }
        });
        mSimpleEditPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mLoginPresenter.login();
                }
                return false;
            }
        });
    }

    private void startHomePageWithSharedElement() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        ActivityOptions transitionActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                        mImageButton.getSharedElement(), getResources().getString(R.string.shared_login_button_home_toolbar));
        startActivity(intent, transitionActivityOptions.toBundle());
    }

    @Override
    public void showProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mImageButton.startScaleAnimation();
            }
        });
    }

    @Override
    public void hideProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mImageButton.dismissProgress();
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                startHomePageWithSharedElement();
            }
        });
    }

    @Override
    public void onLoginFail() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mImageButton.startRecoveryAnimation();
            }
        });
    }

    @Override
    public String getUserName() {
        return mSimpleEditName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mSimpleEditPassword.getText().toString();
    }
}
