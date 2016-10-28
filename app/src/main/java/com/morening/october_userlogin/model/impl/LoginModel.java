package com.morening.october_userlogin.model.impl;

import android.text.TextUtils;

import com.morening.october_userlogin.model.ILoginModel;
import com.morening.october_userlogin.model.OnLoginCallback;

/**
 * Created by morening on 2016/10/26.
 */

public class LoginModel implements ILoginModel {

    @Override
    public void doCheck(final String userName, final String password, final OnLoginCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (TextUtils.equals(userName, "123") && TextUtils.equals(password, "123")){
                    callback.onLoginSuccess();
                } else if (TextUtils.equals(userName, "123") || TextUtils.equals(password, "123")){
                    callback.onLoginFail(1);
                } else {
                    callback.onLoginFail(2);
                }
            }
        }).start();
    }
}
