package com.morening.october_userlogin.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.morening.october_userlogin.R;
import com.morening.october_userlogin.presenter.IUserInfoPresenter;
import com.morening.october_userlogin.presenter.impl.UserInfoPresenter;
import com.morening.october_userlogin.view.IUserInfoView;
import com.morening.october_userlogin.view.activity.HomeActivity;
import com.morening.october_userlogin.view.adapter.DividerItemDecoration;
import com.morening.october_userlogin.view.adapter.ProfileAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements IUserInfoView{

    public static final String TAG = "ProfileFragment";

    private Activity mActivity = null;
    private View mView = null;
    private RecyclerView mRecyclerView = null;
    private ProgressBar mProgressBar = null;
    private ProfileAdapter mProfileAdapter = null;

    private IUserInfoPresenter mUserInfoPresenter = null;

    private Handler mHandler = null;

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
        mHandler = new Handler(Looper.getMainLooper());
        mUserInfoPresenter = new UserInfoPresenter(this);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.id_profile_progressbar);

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
        mProfileAdapter = new ProfileAdapter(getContext());
        mRecyclerView.setAdapter(mProfileAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mUserInfoPresenter.loadUserInfo();
    }

    public void setUserInfo(final List<Pair<String, String>> info) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mProfileAdapter.setData(info);
                mProfileAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
