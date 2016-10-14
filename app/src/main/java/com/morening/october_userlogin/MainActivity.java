package com.morening.october_userlogin;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.morening.october_userlogin.view.AnimationImageButton;
import com.morening.october_userlogin.view.SimpleEditView;

public class MainActivity extends Activity {

    private AnimationImageButton mImageButton = null;
    private SimpleEditView mSimpleEditName = null;
    private SimpleEditView mSimpleEditPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getActionBar().hide();

        mImageButton = (AnimationImageButton) findViewById(R.id.id_user_login_animation_button_login);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        mSimpleEditName = (SimpleEditView) findViewById(R.id.id_user_login_simple_edit_name);
        mSimpleEditPassword = (SimpleEditView) findViewById(R.id.id_user_login_simple_edit_password);
        mSimpleEditName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    mSimpleEditPassword.requestFocus();
                    mSimpleEditPassword.setSelection(mSimpleEditPassword.getCount());
                }

                return false;
            }
        });
        mSimpleEditPassword.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    doLogin();
                }
                return false;
            }
        });
    }

    private void doLogin() {
        // Todo do login logic

        mImageButton.startAnimation(new AnimationImageButton.onProgressStateCallback(){

            @Override
            public void onProgressStart() {
                //Todo implement operation when start progress
            }

            @Override
            public void onProgressEnd() {
                //Todo implement operation when end progress
            }
        });
    }
}
