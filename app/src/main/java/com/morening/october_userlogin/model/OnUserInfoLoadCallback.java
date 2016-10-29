package com.morening.october_userlogin.model;

import android.util.Pair;

import java.util.List;

/**
 * Created by morening on 2016/10/29.
 */

public interface OnUserInfoLoadCallback {

    void onSuccess(List<Pair<String, String>> info);

    void onFail(int errCode);
}
