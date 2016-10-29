package com.morening.october_userlogin.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.morening.october_userlogin.model.ILoginModel;
import com.morening.october_userlogin.model.OnLoginCallback;

/**
 * Created by morening on 2016/10/26.
 */

public class LoginModel implements ILoginModel {

    @Override
    public void doCheck(String userName, String password, OnLoginCallback callback) {

        new LoginAsyncTask(callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                        new LoginData(userName, password));
    }

    class LoginData{
        private String userName = null;
        private String password = null;

        public LoginData(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }

    class LoginAsyncTask extends AsyncTask<LoginData, Integer, Integer>{

        private OnLoginCallback mCallback = null;

        public LoginAsyncTask(OnLoginCallback callback) {
            mCallback = callback;
        }

        @Override
        protected Integer doInBackground(LoginData... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 0x00;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            switch (result){
                case 0x00:
                    mCallback.onLoginSuccess();
                    break;
                default:
                    mCallback.onLoginFail(result);
            }
        }
    }
}
