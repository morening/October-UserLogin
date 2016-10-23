package com.morening.october_userlogin.model;

/**
 * Created by morening on 2016/10/23.
 */

public class MenuItemModule {

    private String mTitle = null;
    private String mSubTitle = null;

    public MenuItemModule setTitle(String title) {
        this.mTitle = title;

        return this;
    }

    public MenuItemModule setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;

        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }
}
