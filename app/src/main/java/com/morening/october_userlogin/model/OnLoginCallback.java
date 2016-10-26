package com.morening.october_userlogin.model;

/**
 * Created by morening on 2016/10/26.
 */

public interface OnLoginCallback {

    void onLoginSuccess();

    void onLoginFail(int errCode);
}
