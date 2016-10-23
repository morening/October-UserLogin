package com.morening.october_userlogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.morening.october_userlogin.model.MenuItemModule;

import java.util.List;

/**
 * Created by morening on 2016/10/23.
 */

public class HomeSlidingMenuAdapter extends RecyclerView.Adapter<HomeSlidingMenuAdapter.HomeSlidingMenuViewHolder> {

    private Context mContext = null;
    private List<MenuItemModule> mDatas = null;

    public HomeSlidingMenuAdapter(Context context, List<MenuItemModule> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public HomeSlidingMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(HomeSlidingMenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    static class HomeSlidingMenuViewHolder extends RecyclerView.ViewHolder{


        public HomeSlidingMenuViewHolder(View itemView) {
            super(itemView);
        }
    }
}
