package com.morening.october_userlogin.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;

import com.morening.october_userlogin.R;
import com.morening.october_userlogin.view.fragment.DetailFragment;
import com.morening.october_userlogin.view.fragment.HomeFragment;
import com.morening.october_userlogin.view.fragment.ProfileFragment;
import com.morening.october_userlogin.view.fragment.SettingsFragment;

/**
 * Created by morening on 2016/10/29.
 */

public class HomeScenarioManager {

    private static final String TAG = "HomeScenarioManager";

    public final static int HOME_HOME_PAGE = 0;
    public final static int HOME_DETAIL_PAGE = 1;
    public final static int HOME_SETTINGS_PAGE = 2;
    public final static int HOME_PROFILE_PAGE = 3;

    private HomeActivity mActivity = null;
    private int mCurPage = HOME_HOME_PAGE;

    public HomeScenarioManager(HomeActivity activity){
        mActivity = activity;
    }

    public void goNextPage(int nextPage){
        if (mCurPage == nextPage){
            return;
        }

        FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (nextPage){
            case HOME_HOME_PAGE:
                fragment = new HomeFragment();
                break;
            case HOME_DETAIL_PAGE:
                fragment = new DetailFragment();
                break;
            case HOME_SETTINGS_PAGE:
                fragment = new SettingsFragment();
                break;
            case HOME_PROFILE_PAGE:
                fragment = new ProfileFragment();
                break;
            default:
                Log.e(TAG, "nextPage: "+nextPage);
        }
        ft.replace(R.id.id_home_container, fragment);
        ft.commit();

        mCurPage = nextPage;
    }

    public int getCurrentPage(){

        return mCurPage;
    }

    public String getCurrentPageName(){

        switch (mCurPage){
            case HOME_HOME_PAGE:
                return HomeFragment.TAG;
            case HOME_DETAIL_PAGE:
                return DetailFragment.TAG;
            case HOME_SETTINGS_PAGE:
                return SettingsFragment.TAG;
            case HOME_PROFILE_PAGE:
                return ProfileFragment.TAG;
        }

        return null;
    }
}
