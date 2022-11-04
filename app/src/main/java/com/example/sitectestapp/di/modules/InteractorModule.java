package com.example.sitectestapp.di.modules;

import com.example.sitectestapp.data.model.MainInteractor;
import com.example.sitectestapp.data.model.UsersInteractor;
import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.ui.base.Interactor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    Interactor.UsersInteractor provideUsersInteractor(DataBaseRoom dataBaseRoom) {
        return new UsersInteractor(dataBaseRoom);
    }

    @Provides
    Interactor.MainInteractor provideMainInteractor(SiTecApi siTecApi, DataBaseRoom dataBaseRoom) {
        return new MainInteractor(siTecApi, dataBaseRoom);
    }
}
