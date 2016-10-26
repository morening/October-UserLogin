package com.morening.october_userlogin.presenter.impl;

import com.morening.october_userlogin.model.ILoginModel;
import com.morening.october_userlogin.model.OnLoginCallback;
import com.morening.october_userlogin.model.impl.LoginModel;
import com.morening.october_userlogin.presenter.ILoginPresenter;
import com.morening.october_userlogin.view.ILoginView;

/**
 * Created by morening on 2016/10/26.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView = null;
    private ILoginModel mLoginModel = null;

    public LoginPresenter(ILoginView loginView) {
        this.mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    @Override
    public void login() {
        String userName = mLoginView.getUserName();
        String password = mLoginView.getPassword();
        mLoginModel.doCheck(userName, password, new OnLoginCallback() {
            @Override
            public void onLoginSuccess() {
                mLoginView.hideProgress();
                mLoginView.onLoginSuccess();
            }

            @Override
            public void onLoginFail(int errCode) {
                mLoginView.hideProgress();
                mLoginView.onLoginFail();
            }
        });
    }
}
