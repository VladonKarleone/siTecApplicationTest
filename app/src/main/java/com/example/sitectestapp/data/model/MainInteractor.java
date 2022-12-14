package com.example.sitectestapp.data.model;

import android.util.Log;

import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.data.response.AuthResponse;
import com.example.sitectestapp.data.response.UsersResponse;
import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.data.room.entities.UserEntity;
import com.example.sitectestapp.ui.base.Interactor;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainInteractor implements Interactor.MainInteractor {

    @Inject
    SiTecApi siTecApi;

    @Inject
    DataBaseRoom dataBaseRoom;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainInteractor(SiTecApi siTecApi, DataBaseRoom dataBaseRoom) {
        this.siTecApi = siTecApi;
        this.dataBaseRoom = dataBaseRoom;
    }

    @Override
    public Single<UsersResponse> getUsersList(String imei) {
        return siTecApi.getUsersList(imei);
    }

    @Override
    public void insertUser(UserEntity user) {
        compositeDisposable.add(
                getUserByUID(user)
                        .subscribeOn(Schedulers.io())
                        .map(userEntityList -> {
                            if (userEntityList.size() == 0) {
                                dataBaseRoom.usersDao().insertUsers(user);
                            }
                            return userEntityList;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userEntityList -> {
                                },
                                throwable -> {
                                    Log.e("DATA_BASE", "MainPresenter#insertOneUser", throwable);
                                }
                        )
        );
    }

    /**
     * ???????????????? ???????????? ?????????????????????????? ???? UID
     *
     * @param user ????????????????????????
     */
    private Single<List<UserEntity>> getUserByUID(UserEntity user) {
        return dataBaseRoom.usersDao().getUserByUid(user.getUid());
    }

    @Override
    public Single<List<UserEntity>> getUsersFromDB() {
        return dataBaseRoom.usersDao().getUsers();
    }

    @Override
    public Single<String> getUid(String user) {
        return dataBaseRoom.usersDao().getUidByUser(user);
    }

    @Override
    public Single<AuthResponse> auth(String imei, String uid, String pass) {
        return siTecApi.performAuthentication(imei, uid, pass, "false", "");
    }

    @Override
    public void insertResponse(ResponsesEntity responsesEntity) {
        Completable.fromAction(() -> {
                    dataBaseRoom.responsesDao().insertResponses(responsesEntity);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.i("DATA_BASE", "Database is updated!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("DATA_BASE", "MiniInteractor#insertResponse", e);
                    }
                });

    }
}
