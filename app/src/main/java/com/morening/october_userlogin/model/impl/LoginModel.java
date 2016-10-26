package com.morening.october_userlogin.model.impl;

import android.text.TextUtils;

import com.morening.october_userlogin.model.ILoginModel;
import com.morening.october_userlogin.model.OnLoginCallback;

/**
 * Created by morening on 2016/10/26.
 */

public class LoginModel implements ILoginModel {

    @Override
    public void doCheck(String userName, String password, OnLoginCallback callback) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (TextUtils.equals(userName, "morening") && TextUtils.equals(password, "123456")){
            callback.onLoginSuccess();
        } else if (TextUtils.equals(userName, "morening") || TextUtils.equals(password, "123456")){
            callback.onLoginFail(1);
        } else {
            callback.onLoginFail(2);
        }
    }
}
