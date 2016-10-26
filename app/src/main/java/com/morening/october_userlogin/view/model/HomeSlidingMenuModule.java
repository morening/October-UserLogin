package com.morening.october_userlogin.view.model;

import com.morening.october_userlogin.view.fragment.DetailFragment;
import com.morening.october_userlogin.view.fragment.HomeFragment;
import com.morening.october_userlogin.view.fragment.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morening on 2016/10/23.
 */

public class HomeSlidingMenuModule {

    static public List<MenuItemModule> getMenuItems(){

        List<MenuItemModule> menuItems = new ArrayList<>();
        menuItems.add(new MenuItemModule().setTitle(HomeFragment.TAG));
        menuItems.add(new MenuItemModule().setTitle(DetailFragment.TAG));
        menuItems.add(new MenuItemModule().setTitle(SettingsFragment.TAG));

        return menuItems;
    }

}
