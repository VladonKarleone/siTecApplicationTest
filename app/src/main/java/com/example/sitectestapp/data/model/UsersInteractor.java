package com.example.sitectestapp.data.model;

import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.ui.base.Interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class UsersInteractor implements Interactor.UsersInteractor {

    @Inject
    DataBaseRoom dataBaseRoom;

    public UsersInteractor(DataBaseRoom dataBaseRoom) {
        this.dataBaseRoom = dataBaseRoom;
    }

    @Override
    public Single<List<ResponsesEntity>> getResponses() {
        return dataBaseRoom.responsesDao().getResponses();
    }
}
