package com.example.sitectestapp;

import android.util.Log;

import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.data.response.ListUsers;
import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.data.room.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainBaseContract.Presenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    DataBaseRoom dataBaseRoom;

    public MainPresenter(DataBaseRoom dataBaseRoom) {
        this.dataBaseRoom = dataBaseRoom;
    }

    private MainBaseContract.View view;

    @Override
    public void bindView(MainBaseContract.View view) {
        this.view = view;
    }

    @Override
    public void getUsersList(SiTecApi siTecApi) {
        compositeDisposable.add(
                siTecApi.getUsersList(view.getIMEI())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    List<ListUsers> userList = response.users.listUsers;
                                    List<UserEntity> users = new ArrayList<>();
                                    try {
                                        for (int i = 0; i < userList.size(); i++) {
                                            UserEntity user = new UserEntity();
                                            user.setUser(userList.get(i).user);
                                            user.setLanguage(userList.get(i).language);
                                            user.setUid(userList.get(i).uid);
                                            users.add(user);
                                        }
                                        insertUser(users);
                                    } catch (Exception e) {
                                        Log.e("ERR", "exception", e);
                                    }

                                },
                                throwable -> {
                                    Log.e("", "Ошибка при получении списка пользователей", throwable);
                                }
                        )
        );
    }

    public void auth(SiTecApi siTecApi, String uid, String pass) {
        compositeDisposable.add(
                siTecApi.performAuthentication(view.getIMEI(), uid, pass, "false", "")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    try {
                                        ResponsesEntity responsesEntity = new ResponsesEntity();
                                        responsesEntity.setResponse(response.authentication.response);
                                        responsesEntity.setContinueWork(response.authentication.continueWork);
                                        responsesEntity.setPhotoHash(response.authentication.photoHash);
                                        responsesEntity.setCurrentDate(response.authentication.currentDate);

                                        insertResponse(responsesEntity);
                                        view.openUsersActivity();
                                    } catch (Throwable throwable) {
                                        view.showToast("Ошибка авторизации");
                                    }

                                },
                                throwable -> {
                                    Log.e("", "Ошибка авторизации", throwable);
                                }
                        )
        );
    }

    @Override
    public void getUsersForSpinner() {
        compositeDisposable.add(
                dataBaseRoom.usersDao().getUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userEntities -> {
                                    List<String> users = new ArrayList<>();
                                    for (int i = 0; i < userEntities.size(); i++) {
                                        users.add(userEntities.get(i).getUser());
                                    }
                                    view.setSpinner(users);
                                },
                                throwable -> {
                                    Log.e("Error", "UsersPresenter#getUsers", throwable);
                                }
                        )
        );
    }

    @Override
    public void setUid(SiTecApi siTecApi, String user, String pass) {
        compositeDisposable.add(
                dataBaseRoom.usersDao().getUIDByUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                uid -> {
                                    auth(siTecApi, uid, pass);
                                },
                                throwable -> {
                                    Log.e("Error", "MainPresenter#SetUid", throwable);
                                }
                        )
        );
    }

    private Disposable insertResponse(ResponsesEntity responsesEntity) {
        return Completable.fromAction(() -> {
                    dataBaseRoom.responsesDao().insertResponses(responsesEntity);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void insertUser(List<UserEntity> users) {
        for (int i = 0; i < users.size(); i++) {
            insertOneUser(users.get(i))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
    }

    private Completable insertOneUser(UserEntity user) {
        return Completable.fromCallable(() -> {
                    return getUserByUID(user)
                            .subscribe(
                                    userEntityList -> {
                                        if (userEntityList.size() == 0) {
                                            dataBaseRoom.usersDao().insertUsers(user);
                                        }
                                        getUsersForSpinner();
                                    },
                                    throwable -> {
                                        Log.e("DATA_BASE", "MainPresenter#insertOneUser", throwable);
                                    }
                            );
                }
        );
    }

    private Single<List<UserEntity>> getUserByUID(UserEntity user) {
        return dataBaseRoom.usersDao().getUserByUID(user.getUid());
    }

}
