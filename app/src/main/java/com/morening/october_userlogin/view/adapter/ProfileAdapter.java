package com.morening.october_userlogin.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.morening.october_userlogin.R;

import java.util.List;

/**
 * Created by morening on 2016/10/20.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context mContext = null;
    private List<Pair<String, String>> mDatas = null;

    public ProfileAdapter(Context context, List<Pair<String, String>> info) {
        mContext = context;
        mDatas = info;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.profile_item_layout, parent, false);

        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        holder.title.setText(mDatas.get(position).first);
        holder.content.setText(mDatas.get(position).second);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder{

        public TextView title = null;
        public TextView content = null;
        public ImageView editIcon = null;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.id_profile_item_title);
            content = (TextView) itemView.findViewById(R.id.id_profile_item_content);
            editIcon = (ImageView) itemView.findViewById(R.id.id_profile_item_edit);
        }
    }

}
