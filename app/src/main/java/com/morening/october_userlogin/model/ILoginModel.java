package com.morening.october_userlogin.model;

/**
 * Created by morening on 2016/10/26.
 */

public interface ILoginModel {

    void doCheck(String userName, String password, OnLoginCallback callback);
}
