package com.example.sitectestapp.ui.users;

import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.ui.base.BaseContract;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UsersBaseContract {
    interface View extends BaseContract.BaseView {
        void setResponses(List<String> responses);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void setText();
    }
}
