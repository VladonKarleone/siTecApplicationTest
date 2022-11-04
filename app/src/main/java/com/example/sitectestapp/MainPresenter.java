package com.example.sitectestapp;

import android.util.Log;

import com.example.sitectestapp.data.response.ListUsers;
import com.example.sitectestapp.data.response.UsersResponse;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.data.room.entities.UserEntity;
import com.example.sitectestapp.ui.base.Interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainBaseContract.Presenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    Interactor.MainInteractor mainInteractor;

    public MainPresenter(Interactor.MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    private MainBaseContract.View view;

    @Override
    public void bindView(MainBaseContract.View view) {
        this.view = view;
    }

    @Override
    public void getUsersList() {
        compositeDisposable.add(
                mainInteractor.getUsersList(view.getIMEI())
                        .subscribeOn(Schedulers.io())
                        .map(response -> {
                            List<UserEntity> userList = getUserEntity(response);

                            for (int i = 0; i < userList.size(); i++) {
                                mainInteractor.insertUser(userList.get(i));
                            }
                            return response;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    List<UserEntity> userList = getUserEntity(response);
                                    List<String> users = new ArrayList<>();

                                    for (int i = 0; i < userList.size(); i++) {
                                        users.add(userList.get(i).getUser());
                                    }
                                    view.addUsersToSpinner(users);
                                },
                                throwable -> {
                                    Log.e("", "Ошибка при получении списка пользователей", throwable);
                                }
                        )
        );
    }

    private List<UserEntity> getUserEntity(UsersResponse usersResponse) {
        List<ListUsers> userList = usersResponse.users.listUsers;
        List<UserEntity> users = new ArrayList<>();
        try {
            for (int i = 0; i < userList.size(); i++) {
                UserEntity user = new UserEntity();
                user.setUser(userList.get(i).user);
                user.setLanguage(userList.get(i).language);
                user.setUid(userList.get(i).uid);
                users.add(user);
            }
        } catch (Exception e) {
            Log.e("ERR", "MainPresenter#getUserEntity", e);
        }
        return users;
    }

    @Override
    public void getUsersForSpinner() {
        compositeDisposable.add(
                mainInteractor.getUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userEntities -> {
                                    List<String> users = new ArrayList<>();
                                    for (int i = 0; i < userEntities.size(); i++) {
                                        users.add(userEntities.get(i).getUser());
                                    }
                                    view.addUsersToSpinner(users);
                                },
                                throwable -> {
                                    Log.e("Error", "UsersPresenter#getUsers", throwable);
                                }
                        )
        );
    }

    @Override
    public void getUidAndAuth(String user, String pass) {
        compositeDisposable.add(
                mainInteractor.getUID(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                uid -> {
                                    auth(uid, pass);
                                },
                                throwable -> {
                                    Log.e("Error", "MainPresenter#SetUid", throwable);
                                }
                        )
        );
    }

    private void auth(String uid, String pass) {
        compositeDisposable.add(
                mainInteractor.auth(view.getIMEI(), uid, pass)
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

                                        mainInteractor.insertResponse(responsesEntity);
                                        view.openUsersActivity();
                                    } catch (Throwable throwable) {
                                        view.showToast("Ошибка авторизации");
                                        Log.e("DATA_BASE", "MainInteractor#auth", throwable);
                                    }
                                },
                                throwable -> {
                                    Log.e("API", "MainInteractor#auth", throwable);
                                }
                        )
        );
    }
}
