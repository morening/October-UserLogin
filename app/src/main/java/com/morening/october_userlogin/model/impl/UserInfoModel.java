package com.morening.october_userlogin.model.impl;

import android.os.AsyncTask;
import android.util.Pair;

import com.morening.october_userlogin.model.IUserInfoModel;
import com.morening.october_userlogin.model.OnUserInfoLoadCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morening on 2016/10/29.
 */

public class UserInfoModel implements IUserInfoModel {

    @Override
    public void fetchUserInfo(OnUserInfoLoadCallback callback) {

        new UserInfoAsyncTask(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    class UserInfoAsyncTask extends AsyncTask<String, Integer, List<Pair<String, String>>>{

        private OnUserInfoLoadCallback mCallback = null;

        public UserInfoAsyncTask(OnUserInfoLoadCallback callback) {
            mCallback = callback;
        }

        @Override
        protected List<Pair<String, String>> doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Pair<String, String>> info = new ArrayList<>();
            info.add(Pair.create("Name", "猫宁"));
            info.add(Pair.create("ID", "morening"));
            info.add(Pair.create("GitHub", "github.com/morening"));
            info.add(Pair.create("Address", "China"));
            return info;
        }

        @Override
        protected void onPostExecute(List<Pair<String, String>> info) {
            super.onPostExecute(info);
            if (info == null){
                mCallback.onFail(0);
            } else {
                mCallback.onSuccess(info);
            }
        }
    }
}
