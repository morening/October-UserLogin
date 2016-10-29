package com.morening.october_userlogin.view;

import android.util.Pair;

import java.util.List;

/**
 * Created by morening on 2016/10/29.
 */

public interface IUserInfoView {

    void setUserInfo(List<Pair<String, String>> info);

    void showProgress();

    void hideProgress();
}
