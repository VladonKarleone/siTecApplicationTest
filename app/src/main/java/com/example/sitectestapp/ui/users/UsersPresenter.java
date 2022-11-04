package com.example.sitectestapp.ui.users;

import android.util.Log;

import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.di.modules.InteractorModule;
import com.example.sitectestapp.ui.base.Interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UsersPresenter implements UsersBaseContract.Presenter {

    @Inject
    public Interactor.UsersInteractor usersInteractor;

    private UsersBaseContract.View view;

    @Override
    public void bindView(UsersBaseContract.View view) {
        this.view = view;
    }

    public UsersPresenter(Interactor.UsersInteractor usersInteractor) {
        this.usersInteractor = usersInteractor;
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void setText() {
        compositeDisposable.add(
                usersInteractor.getResponses()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responsesEntities -> {
                                    try {
                                        List<String> responsesText = new ArrayList<>();
                                        for (int i = 0; i < responsesEntities.size(); i++) {
                                            responsesText.add("Response: " + responsesEntities.get(i).getResponse() + "\n");
                                            responsesText.add("ContinueWork: " + responsesEntities.get(i).getContinueWork()  + "\n");
                                            responsesText.add("PhotoHash: " + responsesEntities.get(i).getPhotoHash()  + "\n");
                                            responsesText.add("CurrentDate: " + responsesEntities.get(i).getCurrentDate()  + "\n");
                                            responsesText.add("---------------------"  + "\n");
                                        }
                                        view.showResponses(responsesText);
                                    } catch (Throwable throwable) {
                                        Log.e("ERROR", "UsersPresenter#setText", throwable);
                                    }
                                },
                                throwable -> {
                                    Log.e("ERROR", "UsersPresenter#setText", throwable);
                                }
                        )
        );
    }
}
