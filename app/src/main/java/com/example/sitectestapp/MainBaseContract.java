package com.example.sitectestapp;

import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.ui.base.BaseContract;

import java.util.List;

import io.reactivex.Completable;

public interface MainBaseContract {
    interface View extends BaseContract.BaseView {
        void setSpinner(List<String> users);

        void openUsersActivity();

        String getIMEI();

    }

    interface Presenter extends BaseContract.Presenter<MainBaseContract.View> {
        void getUsersList(SiTecApi siTecApi);

        void auth(SiTecApi siTecApi, String uid, String pass);

        void getUsersForSpinner();

        void setUid(SiTecApi siTecApi, String user, String pass);
    }


}
