package com.morening.october_userlogin.view;

/**
 * Created by morening on 2016/10/26.
 */

public interface ILoginView {

    void showProgress();

    void hideProgress();

    void onLoginSuccess();

    void onLoginFail();

    String getUserName();

    String getPassword();
}
