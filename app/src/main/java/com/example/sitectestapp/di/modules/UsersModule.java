package com.example.sitectestapp.di.modules;

import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.ui.users.UsersBaseContract;
import com.example.sitectestapp.ui.users.UsersPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {

    @Provides
    UsersBaseContract.Presenter provideUsersPresenter(DataBaseRoom dataBaseRoom) {
        return new UsersPresenter(dataBaseRoom);
    }
}
