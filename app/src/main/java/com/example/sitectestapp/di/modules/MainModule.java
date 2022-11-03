package com.example.sitectestapp.di.modules;

import com.example.sitectestapp.MainBaseContract;
import com.example.sitectestapp.MainPresenter;
import com.example.sitectestapp.data.room.dao.DataBaseRoom;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    MainBaseContract.Presenter provideMainPresenter(DataBaseRoom dataBaseRoom) {
        return new MainPresenter(dataBaseRoom);
    }
}
