package com.morening.october_userlogin.view.adapter;

import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.october_userlogin.R;
import com.morening.october_userlogin.view.activity.HomeActivity;
import com.morening.october_userlogin.view.fragment.DetailFragment;
import com.morening.october_userlogin.view.fragment.HomeFragment;
import com.morening.october_userlogin.view.fragment.SettingsFragment;
import com.morening.october_userlogin.view.model.MenuItemModule;

import java.util.List;

/**
 * Created by morening on 2016/10/23.
 */

public class HomeSlidingMenuAdapter extends RecyclerView.Adapter<HomeSlidingMenuAdapter.HomeSlidingMenuViewHolder> {

    private HomeActivity mActivity = null;
    private List<MenuItemModule> mDatas = null;

    public HomeSlidingMenuAdapter(HomeActivity activity, List<MenuItemModule> mDatas) {
        this.mActivity = activity;
        this.mDatas = mDatas;
    }

    @Override
    public HomeSlidingMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mActivity).inflate(R.layout.home_slidingmenu_item_layout, null);

        return new HomeSlidingMenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeSlidingMenuViewHolder holder, int position) {
        String title = mDatas.get(position).getTitle();
        holder.mTitle.setText(title);
        holder.itemView.setTag(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                String tag = (String) v.getTag();
                if (TextUtils.equals(HomeFragment.TAG, tag)){
                    ft.replace(R.id.id_home_container, new HomeFragment());
                } else if (TextUtils.equals(DetailFragment.TAG, tag)){
                    ft.replace(R.id.id_home_container, new DetailFragment());
                } else if (TextUtils.equals(SettingsFragment.TAG, tag)){
                    ft.replace(R.id.id_home_container, new SettingsFragment());
                }
                ft.commit();
                mActivity.hideSlidingMenu();
            }
        });
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    static class HomeSlidingMenuViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle = null;

        public HomeSlidingMenuViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.id_home_slidingmenu_item_title);
        }
    }
}
