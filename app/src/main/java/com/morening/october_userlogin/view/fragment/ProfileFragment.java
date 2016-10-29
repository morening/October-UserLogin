package com.morening.october_userlogin.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.morening.october_userlogin.R;
import com.morening.october_userlogin.view.activity.HomeActivity;
import com.morening.october_userlogin.view.adapter.DividerItemDecoration;
import com.morening.october_userlogin.view.adapter.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    private Activity mActivity = null;
    private View mView = null;
    private RecyclerView mRecyclerView = null;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mActivity instanceof HomeActivity){
            ((HomeActivity)mActivity).getToolbarLayout().setElevation(0);
        }

        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        setupRecyclerView();

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity instanceof HomeActivity){
            ((HomeActivity)mActivity).getToolbarLayout().setElevation(getResources().getDimensionPixelSize(R.dimen.home_toolbar_layout_elevation));
        }
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_profile_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Pair<String, String>> info = new ArrayList<>();
        loadUserInfo(info);
        mRecyclerView.setAdapter(new ProfileAdapter(getContext(), info));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void loadUserInfo(List<Pair<String, String>> info) {
        info.add(Pair.create("Name", "morening"));
        info.add(Pair.create("ID", "morening"));
        info.add(Pair.create("Barcode", "morening"));
        info.add(Pair.create("Address", "China"));
    }
}
