package com.morening.october_userlogin.presenter.impl;

import android.util.Pair;

import com.morening.october_userlogin.model.IUserInfoModel;
import com.morening.october_userlogin.model.OnUserInfoLoadCallback;
import com.morening.october_userlogin.model.impl.UserInfoModel;
import com.morening.october_userlogin.presenter.IUserInfoPresenter;
import com.morening.october_userlogin.view.IUserInfoView;

import java.util.List;

/**
 * Created by morening on 2016/10/29.
 */

public class UserInfoPresenter implements IUserInfoPresenter {

    private IUserInfoView mUserInfoView = null;
    private IUserInfoModel mUserInfoModel = null;

    public UserInfoPresenter(IUserInfoView userInfoView) {
        this.mUserInfoView = userInfoView;
        this.mUserInfoModel = new UserInfoModel();
    }

    @Override
    public void loadUserInfo() {
        mUserInfoView.showProgress();
        mUserInfoModel.fetchUserInfo(new OnUserInfoLoadCallback() {
            @Override
            public void onSuccess(List<Pair<String, String>> info) {
                mUserInfoView.hideProgress();
                mUserInfoView.setUserInfo(info);
            }

            @Override
            public void onFail(int errCode) {
                mUserInfoView.hideProgress();
            }
        });
    }
}
